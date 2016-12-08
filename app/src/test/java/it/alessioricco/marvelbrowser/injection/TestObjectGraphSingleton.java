package it.alessioricco.marvelbrowser.injection;

import dagger.ObjectGraph;

public class TestObjectGraphSingleton {

    static public void init() {

        final TestAppModule[] modules = {
                new TestAppModule()
        };

        ObjectGraphSingleton.reset();
        ObjectGraphSingleton.setInstance(ObjectGraph.create((Object[]) modules));
    }

    static public ObjectGraph getInstance() {
        return ObjectGraphSingleton.getInstance();
    }

    static public void reset() {
        ObjectGraphSingleton.reset();
    }
}
