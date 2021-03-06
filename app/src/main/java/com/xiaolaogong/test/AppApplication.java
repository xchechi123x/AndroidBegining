package com.xiaolaogong.test;

import android.app.Application;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.xiaolaogong.test.net.base.RequestFactory;


import okhttp3.Cache;

/**
 * Created by chechi on 2017/7/4.
 */

public class AppApplication extends Application {

    public static AppApplication instance;

    public final static String SHARED_PREFERENCE_NAME = "SC_SHARED_PREFERENCE";

    public final String VERSION = "0.1.0";

    public final String baseUri = "http://192.168.1.127:3100";

    public RequestFactory requestFactory;

    public AMapLocationClient locationClient;

    public AMapLocationClientOption locationOption = new AMapLocationClientOption();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        requestFactory = RequestFactory.getInstance(baseUri);
        locationClient = new AMapLocationClient(getApplicationContext());
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationOption.setInterval(20000);
    }

}
