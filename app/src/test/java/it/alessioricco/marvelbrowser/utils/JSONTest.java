package it.alessioricco.marvelbrowser.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowResources;

import it.alessioricco.marvelbrowser.BuildConfig;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@Config(shadows = { ShadowResources.class },
        sdk = TestEnvironment.sdk,
        constants = BuildConfig.class,
        manifest = TestEnvironment.manifest)
@RunWith(CustomRobolectricTestRunner.class)
public class JSONTest {


    @Test
    public void test_loadJSONFromAsset_with_well_formed_json() throws Exception {

//        String json = MockJsonBuilder.getJson();
//
//        Context context = mock(Context.class);
//        AssetManager assetManager = mock(AssetManager.class);
//
//        when(assetManager.open("")).thenReturn(new ByteArrayInputStream(json.getBytes()));
//        when(context.getAssets()).thenReturn(assetManager);
//
//        // reading json
//        String jsonFromAsset = JsonUtils.loadJSONFromAsset(context,"");
//        assertThat(jsonFromAsset).isNotNull();
//        assertThat(jsonFromAsset).isNotEmpty();
//
//        // testing json
//        Type listType = new TypeToken<List<Journey>>(){}.getType();
//        List<Journey> listFromAsset = new Gson().fromJson(jsonFromAsset, listType);
//        assertThat(listFromAsset).isNotNull();
//        assertThat(listFromAsset).isNotEmpty();
//
//        List<Journey> listFromString = new Gson().fromJson(json, listType);
//        assertThat(listFromString).isNotNull();
//        assertThat(listFromString).isNotEmpty();
//
//        assertThat(listFromAsset.size()).isEqualTo(listFromString.size());
//
//        // are they equal?
//        for (int i=0; i<listFromAsset.size(); i++) {
//            assertThat(listFromAsset.get(i)).isEqualTo(listFromString.get(i));
//        }
//
//        assertThat(listFromAsset.get(0).ticketPrices).isNotNull();

    }

}