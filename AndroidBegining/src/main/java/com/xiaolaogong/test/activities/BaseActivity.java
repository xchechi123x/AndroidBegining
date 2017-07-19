package com.xiaolaogong.test.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.amap.api.location.AMapLocationClient;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xiaolaogong.test.AppApplication;
import com.xiaolaogong.test.net.base.RequestFactory;

/**
 * Created by chechi on 2017/7/4.
 */

public class BaseActivity extends RxAppCompatActivity {

    protected RxPermissions permissions;

    /**
     * 获取APP应用上下文
     *
     * @return AppApplication
     */
    protected AppApplication getAppApplication() {
        return (AppApplication) getApplication();
    }

    /**
     * 获取创建请求的工厂类
     *
     * @return
     */
    protected RequestFactory getRequestFactory() {
        return ((AppApplication) getApplication()).requestFactory;
    }

    /**
     * 获取高德地图的定位服务
     *
     * @return
     */
    protected AMapLocationClient getLocationClient() {
        return ((AppApplication) getApplication()).locationClient;
    }

    /**
     * 创建请求,获取请求结果
     *
     * @param model
     * @param <T>
     * @return <T> T
     */
    public <T> T createRequest(Class<T> model) {
        return ((AppApplication) getApplication()).requestFactory.createRequest(model);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissions = new RxPermissions(this);

    }

    protected int getScreenHeight() {
        return getAppApplication().getResources().getDisplayMetrics().heightPixels;
    }

    protected int getScreenWidth() {
        return getAppApplication().getResources().getDisplayMetrics().widthPixels;
    }

}
