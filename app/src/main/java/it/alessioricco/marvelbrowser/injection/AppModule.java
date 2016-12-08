package it.alessioricco.marvelbrowser.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.alessioricco.marvelbrowser.activities.list.ComicsListActivity;
import it.alessioricco.marvelbrowser.api.RestAdapterFactory;
import it.alessioricco.marvelbrowser.service.MarvelComicsService;
import it.alessioricco.marvelbrowser.App;


/**
 *
 * The class providing all the factories to be used for dependency injection with dagger
 */

@Module(
        injects = {
                App.class,
                MarvelComicsService.class,
                ComicsListActivity.class,
                RestAdapterFactory.class
        },
        library = true)

public class AppModule {

    private App app; // App: constructor
    private App testApp; // Test: constructor and environment

    /**
     * constructor for the main android app
     * @param app the application itself
     */
    public AppModule(App app) {
        this.app = app;
    }

    /**
     * constructor for unit test
     */
    public AppModule() {
        try {
            if (testApp == null) {
                testApp = new App();
            }
            app = testApp;
        } catch (Exception e)
        {/* do nothing */}
    }

    @Provides @Singleton public Context provideContext() {
        return app;
    }

    /**
     * RestAdapter factory
     * used to build a restadapter for the default ticker service endpoint
     * @return a well formed RestAdapterFactory object
     */
    @Provides @Singleton public RestAdapterFactory provideRestAdapter() {
        return new RestAdapterFactory(app);
    }

}
