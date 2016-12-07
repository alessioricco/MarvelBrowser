package it.alessioricco.marvelbrowser.injection;

import dagger.ObjectGraph;

/**
 * Object graph for Dagger
 */
public class ObjectGraphSingleton {
    private static ObjectGraph instance = null;

    public static ObjectGraph getInstance() {
        return ObjectGraphSingleton.instance;
    }

    public static void reset() {
        ObjectGraphSingleton.instance = null;
    }

    public static void setInstance(ObjectGraph instance) {
        if (ObjectGraphSingleton.instance == null) {
            ObjectGraphSingleton.instance = instance;
        } else {
            throw new RuntimeException("Invalid assignment: the instance has been signed already");
        }

    }
}
