package it.alessioricco.marvelbrowser.activities;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowResources;
import org.robolectric.util.ActivityController;

import java.util.List;

import it.alessioricco.marvelbrowser.BuildConfig;
import it.alessioricco.marvelbrowser.activities.list.ComicsListActivity;
import it.alessioricco.marvelbrowser.models.comics.Data;
import it.alessioricco.marvelbrowser.utils.CustomRobolectricTestRunner;
import it.alessioricco.marvelbrowser.utils.TestEnvironment;

import static org.assertj.core.api.Java6Assertions.assertThat;

@Config(shadows = { ShadowResources.class },
        sdk = 21, //TestEnvironment.sdk,
        constants = BuildConfig.class,
        manifest = TestEnvironment.manifest)
@RunWith(CustomRobolectricTestRunner.class)
public class TestComicsListActivity {

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
