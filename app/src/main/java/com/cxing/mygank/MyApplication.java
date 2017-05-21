package com.cxing.mygank;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.anupcowkur.reservoir.Reservoir;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;


/**
 * Created by zhao on 17-5-21.
 */

public class MyApplication extends Application {

    private static MyApplication sApplicationInstance;
    private static final String TAG = "Gank";
    private static final String DATE_FORMATE = "yyyy-MM-dd 'T' HH:mm:ss";
    private Gson mGlobalGson;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationInstance = this;
        Logger.init(TAG);
        this.mGlobalGson = new GsonBuilder().setDateFormat(DATE_FORMATE).create();
        initReservoir();
    }

    private void initReservoir() {
        try {
            Reservoir.init(this,Constants.ONE_MB*3, mGlobalGson);
        } catch (Exception e) {
//            e.printStackTrace();
            Logger.e(e,"initReservoir");
        }
    }

    public static Application getApplicationInstance(){
        return sApplicationInstance;
    }

    public Gson getGlobalGson(){
        return mGlobalGson;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
