package it.alessioricco.marvelbrowser.activities.list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.alessioricco.marvelbrowser.R;
import it.alessioricco.marvelbrowser.activities.details.ComicsDetailActivity;
import it.alessioricco.marvelbrowser.activities.list.adapter.ComicsListViewAdapter;
import it.alessioricco.marvelbrowser.injection.ObjectGraphSingleton;
import it.alessioricco.marvelbrowser.models.comics.Comics;
import it.alessioricco.marvelbrowser.models.comics.Price;
import it.alessioricco.marvelbrowser.models.comics.Result;
import it.alessioricco.marvelbrowser.service.MarvelComicsService;
import it.alessioricco.marvelbrowser.utils.NetworkStatus;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * An activity representing a list of Journeys. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ComicsDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ComicsListActivity extends AppCompatActivity {
    private final String TAG = ComicsListActivity.class.getSimpleName();

    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.comic_list)
    RecyclerView recyclerView;

    @InjectView(R.id.number_of_pages)
    TextView numberOfPages;

    @Inject
    MarvelComicsService MarvelComicsService;

    private String availableBudget = "";

    int currentNetworkStatus = NetworkStatus.NOCONNECTION;
    //private List<Result> comics;
    private ComicsListViewAdapter comicsListViewAdapter;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(getTitle());
        }

        if (findViewById(R.id.comicbook_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        //customization
        initialize();
    }

    /**
     * initialize the custom objects
     */
    private void initialize() {

        ObjectGraphSingleton.getInstance().inject(this);
        ButterKnife.inject(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                fetchComics();
            }
        });


        comicsListViewAdapter = new ComicsListViewAdapter(null, this, mTwoPane);
        recyclerView.setAdapter(comicsListViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.budget) {
            onShowBudgetDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void onShowBudgetDialog() {

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(availableBudget.toString());

        new AlertDialog
                .Builder(this)
                .setView(input)
                .setTitle(R.string.insert_available_budget)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        availableBudget = input.getText().toString();
                        fetchComics();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create()
                .show();
    }

    private void setBudgetValue() {
        fetchComics();
    }

    @Override
    public void onResume() {
        super.onResume();

        fetchComics();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // to avoid memory leaks
        compositeSubscription.unsubscribe();
    }

    private void  showUpdateErrorMessage(int res) {
        Snackbar.make(recyclerView, res, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fetchComics();
                    }
                }).show();
    }

    void startProgress() {

        if (swipeRefreshLayout.isRefreshing()) {
            return;
        }

    }

    /**
     * remove the progress dialog
     *
     * it dismiss the the dialog to prevent a leak
     * http://stackoverflow.com/questions/6614692/progressdialog-how-to-prevent-leaked-window
     */
    void endProgress() {

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
            return;
        }

    }

    /**
     * Display an error message with a retry button
     */
    private void showDownloadErrorMessage() {

        showErrorMessage(R.string.error_retrieving_list);
    }

    /**
     * Display a network error message with a retry button
     */
    private void showNetworkErrorMessage() {

        showErrorMessage(R.string.error_network);
    }

    private void showErrorMessage(int resourceId) {

        Snackbar.make(recyclerView, resourceId, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fetchComics();
                    }
                })
                .show();
    }

    private void fetchComics() {
        // it will fetch images
        if (compositeSubscription.isUnsubscribed()) {
            return;
        }

        //todo: we should handle an event
        try {
            currentNetworkStatus = NetworkStatus.isInternetConnected(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, String.format("network status %d", currentNetworkStatus));
        if (currentNetworkStatus == NetworkStatus.NOCONNECTION) {
            showNetworkErrorMessage();
            return;
        }

        compositeSubscription.add(getComicsSubscription());
    }

    private void UpdateNumberOfPages(List<Result> comics) {

        int pages = 0;
        for(Result comicBook : comics) {
            pages += comicBook.getPageCount();
        }
        numberOfPages.setText(String.format("%d pages", pages));
    }

    private Subscription getComicsSubscription() {
        startProgress();
        final Observable<Comics> observable = MarvelComicsService.getComicsList();

        return observable
                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Comics>() {
                    @Override
                    public void onCompleted() {
                        endProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e != null) {
                            Log.e(TAG, e.getLocalizedMessage());
                        }
                        endProgress();
                        showDownloadErrorMessage();
                    }

                    @Override
                    public void onNext(Comics feed) {

                        Double budget = -1.0;

                        try {
                            budget = Double.parseDouble(availableBudget);
                        }catch (Exception e) {

                        }

                        List<Result> filteredResult;

                        if (budget >= 0) {
                            Collections.sort(feed.getData().getResults(), new Comparator<Result>() {
                                @Override
                                public int compare(Result o, Result t1) {
                                    return o.getPrices().get(0).getPrice().compareTo(t1.getPrices().get(0).getPrice());
                                }
                            });

                            filteredResult = new ArrayList<>();
                            for (Result result : feed.getData().getResults()) {

                                if (result.getPrices() == null) {
                                    continue;
                                }
                                final Price price = result.getPrices().get(0);
                                if (price == null) {
                                    continue;
                                }
                                if (budget >= price.getPrice()) {
                                    budget -= price.getPrice();
                                    filteredResult.add(result);
                                }

                            }
                        } else {

                            filteredResult = feed.getData().getResults();

                        }
                        comicsListViewAdapter.setComics(filteredResult);
                        comicsListViewAdapter.notifyDataSetChanged();
                        UpdateNumberOfPages(filteredResult);
                    }
                });

    }
}
