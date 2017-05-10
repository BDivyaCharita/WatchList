package com.example.divya.watchlist;

import android.app.Application;
import android.content.Context;

/**
 * Created by Divya on 09-05-2017.
 */

public class MyApplication extends Application {

    private static MyApplication sInstance;
    public static String API_KEY_TMDB = "6b7085c6deee4086616c8dae1c1ada12";

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getsInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}

