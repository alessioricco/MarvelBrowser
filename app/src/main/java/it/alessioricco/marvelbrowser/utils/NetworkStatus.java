package it.alessioricco.marvelbrowser.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {

    public final static int WIFI = 1;
    public final static int MOBILE = 2;
    public final static int NOCONNECTION = -1;
    public final static int OTHERNETWORK= -2;

    public static int isInternetConnected (Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) {
            return NOCONNECTION;
        }
        // connected to the internet
        if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
            // connected to wifi
            return WIFI;
        }
        if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
            // connected to the mobile provider's data plan
            return MOBILE;
        }
        return OTHERNETWORK;
    }

}
