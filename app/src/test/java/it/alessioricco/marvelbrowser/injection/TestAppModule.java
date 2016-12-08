package it.alessioricco.marvelbrowser.injection;

import android.content.Context;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.alessioricco.marvelbrowser.activities.TestAdapter;
import it.alessioricco.marvelbrowser.activities.TestComicsListActivity;
import it.alessioricco.marvelbrowser.api.RestAdapterFactory;
import it.alessioricco.marvelbrowser.mock.MockAppWebServer;
import it.alessioricco.marvelbrowser.mock.ShadowRestAdapterFactory;

@Module(includes = {
        AppModule.class
},
        injects = {
                // here the list of classes using injection
                AppModule.class,
                TestAdapter.class,
                TestComicsListActivity.class,
                ShadowRestAdapterFactory.class,
//                TestMarketService.class,
//                TestCurrentSelection.class,
//                TestHistoryService.class,
//                TestFeedService.class,
                MockAppWebServer.class
//                ShadowHistoryService.class,
//                TestMainActivity.class,
//                TestNetworkStatus.class,
//                TestRSSListAdapters.class,
//                ShadowHttpClientFactory.class,
//                ShadowRestAdapterFactory.class
        },
        library = true, overrides = true)
public class TestAppModule {

    private final Context context;

    TestAppModule() {
        context = ShadowApplication.getInstance().getApplicationContext();
    }

    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }


    @Provides
    @Singleton
    public MockAppWebServer getWebServer() {
        return new MockAppWebServer();
    }

    /**
     * RestAdapter factory
     * used to build a restadapter for the default ticker service endpoint
     * @return a well formed RestAdapterFactory object
     */
    @Provides @Singleton public RestAdapterFactory provideRestAdapter() {
        return new ShadowRestAdapterFactory(RuntimeEnvironment.application);
    }

//    @Provides @Singleton public HttpClientFactory provideHttpClientfactory() {
//        return new ShadowHttpClientFactory(context);
//    }
//
//    @Provides
//    public @Singleton
//    HistoryService provideHistoryService() {
//        return new ShadowHistoryService();
//    }
}

