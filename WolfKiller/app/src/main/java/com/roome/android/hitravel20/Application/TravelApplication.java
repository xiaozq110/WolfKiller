package com.roome.android.hitravel20.Application;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/4/19.
 */

public class TravelApplication extends Application {
    public static final String  AppID = "3064551f684ec317311b0f0aa0540965";
    public static SharedPreferences mTravelPreference;
    @Override
    public void onCreate() {
        Bmob.initialize(this,AppID);
        mTravelPreference = PreferenceManager.getDefaultSharedPreferences(this);
        Log.i("TAG","Bmob.initialize");
        super.onCreate();
    }
}
