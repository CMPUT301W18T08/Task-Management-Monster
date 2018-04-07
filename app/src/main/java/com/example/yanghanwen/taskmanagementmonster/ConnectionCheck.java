package com.example.yanghanwen.taskmanagementmonster;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Terrence on 03/04/2018.
 * taken from https://stackoverflow.com/questions/30343011/how-to-check-if-an-android-device-is-online
 * 07/04/2018
 */

public class ConnectionCheck {
    public static boolean isNetWorkAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()){
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }
}
