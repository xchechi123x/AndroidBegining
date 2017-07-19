package com.xiaolaogong.test.common.tools;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import com.xiaolaogong.test.AppApplication;

/**
 * Created by chechi on 2017/7/6.
 */

public class Tools {

    public static int fetchContextColor(Context context, int androidAttribute) {

        TypedValue typedValue = new TypedValue();

        TypedArray colorArray = context.obtainStyledAttributes(typedValue.data, new int[]{androidAttribute});

        int color = colorArray.getColor(0, 0);

        colorArray.recycle();

        return color;
    }

    public static int getScreenWidth(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Point size = new Point();

        wm.getDefaultDisplay().getSize(size);

        return size.x;
    }

    public static int dp2px(Application application, float dp) {
        return (int) (0.5F + dp * application.getResources().getDisplayMetrics().density);
    }

    public static int px2dp(Application application, float px) {
        return (int) (px / application.getResources().getDisplayMetrics().density + 0.5f);
    }


    public static String getStringPreferences(Context context, String name) {
        return context.getSharedPreferences(AppApplication.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getString(name, "");

    }

    public static String setStringPreferences(Context context, String name, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppApplication.SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putString(name, value);
        editor.commit();
        return value;
    }

    public static long getLongPreferences(Context context, String name) {
        return context.getSharedPreferences(AppApplication.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getLong(name, 0);

    }

    public static long setLongPreferences(Context context, String name, long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppApplication.SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putLong(name, value);
        editor.commit();
        return value;
    }

    public static int getIntPreferences(Context context, String name) {
        return context.getSharedPreferences(AppApplication.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getInt(name, 0);

    }

    public static int setIntPreferences(Context context, String name, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppApplication.SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putInt(name, value);
        editor.commit();
        return value;
    }

    public static boolean getBooleanPreferences(Context context, String name) {
        return context.getSharedPreferences(AppApplication.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getBoolean(name, false);

    }

    public static boolean setIntPreferences(Context context, String name, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(AppApplication.SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putBoolean(name, value);
        editor.commit();
        return value;
    }
}
