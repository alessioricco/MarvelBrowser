package it.alessioricco.marvelbrowser.activities;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowResources;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import it.alessioricco.marvelbrowser.BuildConfig;
import it.alessioricco.marvelbrowser.activities.list.ComicsListActivity;
import it.alessioricco.marvelbrowser.injection.TestObjectGraphSingleton;
import it.alessioricco.marvelbrowser.mock.MockAppWebServer;
import it.alessioricco.marvelbrowser.models.comics.Comics;
import it.alessioricco.marvelbrowser.models.comics.Data;
import it.alessioricco.marvelbrowser.models.comics.Result;
import it.alessioricco.marvelbrowser.service.MarvelComicsService;
import it.alessioricco.marvelbrowser.utils.CustomRobolectricTestRunner;
import it.alessioricco.marvelbrowser.utils.TestEnvironment;
import okhttp3.mockwebserver.MockWebServer;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.assertj.core.api.Java6Assertions.assertThat;

@Config(shadows = { ShadowResources.class },
        sdk = 21, //TestEnvironment.sdk,
        constants = BuildConfig.class,
        manifest = TestEnvironment.manifest)
@RunWith(CustomRobolectricTestRunner.class)
public class TestComicsListActivity {

    @Inject
    MarvelComicsService MarvelComicsService;

    @Inject
    MockAppWebServer mockWebServer;

    @Before
    public void init() throws Exception {

        // Init the IoC and inject us
        TestObjectGraphSingleton.init();
        TestObjectGraphSingleton.getInstance().inject(this);

        mockWebServer.start();
    }

    @After
    public void shutdown() throws Exception {

        mockWebServer.shutdown();
    }

    @Test
    public void test_activity_populate_journeys_model() throws Exception {

        // Create the activity
        ActivityController<ComicsListActivity> activityController =
                Robolectric.buildActivity(ComicsListActivity.class)
                        .create()
                        .start()
                        .resume()
                        .visible();
        ComicsListActivity activity = activityController.get();
        assertThat(activity).isNotNull();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        assertThat(shadowActivity).isNotNull();
        assertThat(shadowActivity.isFinishing()).isFalse();

    }

}
