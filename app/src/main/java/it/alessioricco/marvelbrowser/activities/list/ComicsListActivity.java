package it.alessioricco.marvelbrowser.activities.list;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.alessioricco.marvelbrowser.R;
import it.alessioricco.marvelbrowser.activities.details.ComicsDetailActivity;
import it.alessioricco.marvelbrowser.activities.list.adapter.ComicsListViewAdapter;
import it.alessioricco.marvelbrowser.models.comics.Comics;
import it.alessioricco.marvelbrowser.utils.JsonUtils;
import it.alessioricco.marvelbrowser.utils.StringUtils;

/**
 * An activity representing a list of Journeys. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ComicsDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ComicsListActivity extends AppCompatActivity {

    private Comics comics;

    @InjectView(R.id.comic_list)
    RecyclerView recyclerView;

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
        ButterKnife.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        updateJourneyList();

    }

    private void updateJourneyList() {

        final String json = JsonUtils.loadJSONFromAsset(this,getString(R.string.json_filename));
        if (StringUtils.isNullOrEmpty(json)) {
            showUpdateErrorMessage(R.string.error_list_not_available);
            return;
        }

        // this is the trick for array deserialization
        //Type listType = new TypeToken<Comics>(){}.getType();
        comics = new Gson().fromJson(json, Comics.class);

        if (comics == null) {
            // implement a snackbar
            showUpdateErrorMessage(R.string.error_retrieving_list);
            return;
        }

        recyclerView.setAdapter(new ComicsListViewAdapter(comics, this, mTwoPane));
    }

    private void  showUpdateErrorMessage(int res) {
        Snackbar.make(recyclerView, res, Snackbar.LENGTH_LONG)
                .setAction(R.string.Retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateJourneyList();
                    }
                }).show();
    }
}
