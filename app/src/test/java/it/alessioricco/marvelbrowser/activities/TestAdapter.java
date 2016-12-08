package it.alessioricco.marvelbrowser.activities;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowResources;

import java.util.ArrayList;
import java.util.List;

import it.alessioricco.marvelbrowser.BuildConfig;
import it.alessioricco.marvelbrowser.activities.list.ComicsListActivity;
import it.alessioricco.marvelbrowser.activities.list.adapter.ComicsListViewAdapter;
import it.alessioricco.marvelbrowser.injection.TestObjectGraphSingleton;
import it.alessioricco.marvelbrowser.models.comics.Result;
import it.alessioricco.marvelbrowser.models.comics.Thumbnail;
import it.alessioricco.marvelbrowser.utils.CustomRobolectricTestRunner;
import it.alessioricco.marvelbrowser.utils.TestEnvironment;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

@Config(shadows = { ShadowResources.class },
        sdk = TestEnvironment.sdk,
        constants = BuildConfig.class,
        manifest = TestEnvironment.manifest)
@RunWith(CustomRobolectricTestRunner.class)
public class TestAdapter {

    @Before
    public void init() throws Exception {

        // Init the IoC and inject us
        TestObjectGraphSingleton.init();
        TestObjectGraphSingleton.getInstance().inject(this);

    }

    /**
     * Method executed after any test
     */
    @After
    public void tearDown() {

        TestObjectGraphSingleton.reset();

    }

    /**
     * GIVEN a feed item
     * WHEN we'll bind the item to the recycle view using the adapter
     * THEN the recycleView associated viewHolder will be populated with the feed item values
     *
     * @throws Exception
     */
    @Test
    public void testAdapterWithWellFormedFeed() throws Exception {

        Context context = RuntimeEnvironment.application;

        final List<Result> feedItemList = new ArrayList<>();

        Result item1 = new Result();
        item1.setTitle("title");

        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setExtension(".jpg");
        thumbnail.setPath("/fakepath");
        item1.setThumbnail(thumbnail);

        feedItemList.add(item1);

        ComicsListActivity activity = mock(ComicsListActivity.class);
        Mockito.when(activity.getApplication()).thenReturn(RuntimeEnvironment.application);

        // add the feed to the adapter
        final ComicsListViewAdapter rssAdapter = new ComicsListViewAdapter(activity, false);
        rssAdapter.setComics(feedItemList);
        assertThat(rssAdapter.getItemCount()).isEqualTo(1);

        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setAdapter(rssAdapter);

        // get the adapter from the recyclerview
        final RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter.getItemCount()).isEqualTo(1);

        // test layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        assertThat(layoutManager).isNotNull();

        // test view holder
        final ComicsListViewAdapter.ViewHolder viewHolder = rssAdapter.onCreateViewHolder(recyclerView, 0);
        assertThat(viewHolder).isNotNull();

        // test binding
        rssAdapter.onBindViewHolder(viewHolder, 0);
        assertThat(viewHolder.title.getText().toString()).isEqualTo(item1.getTitle());
    }
}