package it.alessioricco.marvelbrowser.activities.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.alessioricco.marvelbrowser.R;
import it.alessioricco.marvelbrowser.activities.list.ComicsListActivity;
import it.alessioricco.marvelbrowser.models.comics.Result;
import it.alessioricco.marvelbrowser.models.comics.Thumbnail;
import it.alessioricco.marvelbrowser.utils.ComicBookCoverUrlHelper;
import it.alessioricco.marvelbrowser.utils.ImageDownloader;

/**
 * An activity representing a single Journey detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ComicsListActivity}.
 */
public class ComicsDetailActivity extends AppCompatActivity {

    @InjectView(R.id.background_cover)
    ImageView cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comicbook_detail);

        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            final Bundle arguments = new Bundle();
            final String argId = ComicsDetailFragment.ARG_COMICBOOK;

            final Result comicBook = (Result) getIntent().getSerializableExtra(argId);
            if (comicBook == null) {
                return;
            }

            arguments.putSerializable(argId, comicBook);

            final Thumbnail thumbnail = comicBook.getThumbnail();
            final String url = ComicBookCoverUrlHelper.getBigCover(thumbnail.getPath(),thumbnail.getExtension());
            ImageDownloader.go(this.getApplicationContext(),url,cover);

            final ComicsDetailFragment fragment = new ComicsDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.comicbook_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, ComicsListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
