package it.alessioricco.marvelbrowser.activities.details;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.alessioricco.marvelbrowser.R;
import it.alessioricco.marvelbrowser.activities.list.ComicsListActivity;
import it.alessioricco.marvelbrowser.models.comics.Creators;
import it.alessioricco.marvelbrowser.models.comics.Item;
import it.alessioricco.marvelbrowser.models.comics.Price;
import it.alessioricco.marvelbrowser.models.comics.Result;


/**
 * A fragment representing a single Journey detail screen.
 * This fragment is either contained in a {@link ComicsListActivity}
 * in two-pane mode (on tablets) or a {@link ComicsDetailActivity}
 * on handsets.
 */
public class ComicsDetailFragment extends Fragment {

    @InjectView(R.id.comicbook_title)
    TextView title;

    @InjectView(R.id.comicbook_description)
    TextView description;

    @InjectView(R.id.comicbook_authors)
    TextView authors;

    @InjectView(R.id.comicbook_pages)
    TextView pages;

    @InjectView(R.id.comicbook_cost)
    TextView cost;


    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_COMICBOOK = "comicbook";

    /**
     * The dummy content this fragment is presenting.
     */
    //private Journey journey;
    private Result comicBook;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ComicsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_COMICBOOK)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            comicBook = (Result) getArguments().getSerializable(ARG_COMICBOOK);
            if (comicBook == null) {
                return;
            }

            final Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {

                appBarLayout.setTitle(comicBook.getTitle());
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comicbook_detail, container, false);

        ButterKnife.inject(this, rootView);

        // Show the dummy content as text in a TextView.
        if (comicBook != null) {
            title.setText(comicBook.getTitle());
            description.setText(comicBook.getDescription());

            pages.setText(String.format("%d pages",comicBook.getPageCount()));

            StringBuilder prices = new StringBuilder();
            for(Price price: comicBook.getPrices()) {
                prices.append(String.format("%s (%.2f$) ", price.getType(), price.getPrice()));
            }
            this.cost.setText(prices);

            StringBuilder authors = new StringBuilder();
            for(Item creator: comicBook.getCreators().getItems()) {
                prices.append(String.format("%s (%s) ", creator.getName(), creator.getRole()));
            }
            this.authors.setText(authors);

        }

        return rootView;
    }
}
