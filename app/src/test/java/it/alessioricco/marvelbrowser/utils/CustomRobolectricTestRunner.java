package it.alessioricco.marvelbrowser.utils;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.internal.bytecode.InstrumentationConfiguration;


/**
 * Custom Robolectric Runner, useful for custom actions
 */
public class CustomRobolectricTestRunner extends RobolectricTestRunner {

    public CustomRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

//    //http://stackoverflow.com/questions/31920865/how-to-create-custom-shadows-in-robolectric-3-0
    public InstrumentationConfiguration createClassLoaderConfig() {
        InstrumentationConfiguration.Builder builder = InstrumentationConfiguration.newBuilder();
        return builder.build();
    }
}

