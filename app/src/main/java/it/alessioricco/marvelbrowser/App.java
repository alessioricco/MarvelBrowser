package it.alessioricco.marvelbrowser;

import android.app.Application;

import java.util.Collections;
import java.util.List;

import dagger.ObjectGraph;
import it.alessioricco.marvelbrowser.injection.AppModule;
import it.alessioricco.marvelbrowser.injection.ObjectGraphSingleton;
import st.lowlevel.storo.Storo;
import st.lowlevel.storo.StoroBuilder;


public final class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize ObjectGraph for dependency Injection
        if (ObjectGraphSingleton.getInstance() == null) {
            ObjectGraph objectGraph = ObjectGraph.create(getModules().toArray());
            ObjectGraphSingleton.setInstance(objectGraph);
            objectGraph.inject(this);
        }


        if (!Storo.isInitialized()) {
            StoroBuilder.configure(100 * 1024)
                    .setDefaultCacheDirectory(this)
                    .initialize();
        }

    }

    private List<Object> getModules() {
        return Collections.<Object>singletonList(new AppModule(this));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ObjectGraphSingleton.reset();
    }

}

