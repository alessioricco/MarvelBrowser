package it.alessioricco.marvelbrowser.utilities;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * JSON Utilities
 */
public class JsonUtils {

    /**
     * from http://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
     * read a json file from the asset folder
     * @param context   the current context
     * @param fileName  the json filename
     * @return          the file content as a string
     */
    public static String loadJSONFromAsset(Context context, String fileName) {
        final String json;
        try {

            final InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}
