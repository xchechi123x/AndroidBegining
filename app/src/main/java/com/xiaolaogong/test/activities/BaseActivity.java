package com.xiaolaogong.test.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.amap.api.location.AMapLocationClient;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xiaolaogong.test.AppApplication;
import com.xiaolaogong.test.net.base.RequestFactory;

/**
 * Created by chechi on 2017/7/4.
 */

public class BaseActivity extends RxAppCompatActivity {

    /**
     * 动态权限申请
     */
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

    /**
     * 获取屏幕高度
     *
     * @return
     */
    protected int getScreenHeight() {
        return getAppApplication().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    protected int getScreenWidth() {
        return getAppApplication().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 全屏设置
     */
    protected void fullCreen() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

}
