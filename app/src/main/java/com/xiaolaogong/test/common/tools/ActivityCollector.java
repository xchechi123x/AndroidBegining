package com.xiaolaogong.test.common.tools;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chechi on 2017/8/13.
 */

public class ActivityCollector {

    public static Map<String, Activity> map = new HashMap<>();

    public static void addActivity(Activity activity) {
        map.put(activity.getClass().getName(), activity);
    }

    public static void remove(Activity activity) {
        map.remove(activity.getClass().getName());
    }

    public static void finish(Activity activity) {
        map.get(activity.getClass().getName()).finish();
    }

    public static void finishAll() {
        if (!map.isEmpty()) {
            while (map.values().iterator().hasNext()) {
                map.values().iterator().next().finish();
            }
        }
    }

}
