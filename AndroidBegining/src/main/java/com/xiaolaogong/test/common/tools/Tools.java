package com.xiaolaogong.test.common.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.WindowManager;

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
}
