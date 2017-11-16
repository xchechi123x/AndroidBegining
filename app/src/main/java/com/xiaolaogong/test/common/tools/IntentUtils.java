package com.xiaolaogong.test.common.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by chechi on 2017/8/27.
 */

public class IntentUtils {

    private IntentUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated, and its methods must be called directly.");
    }

    /**
     * 跳转到指定url
     *
     * @param context 当前上下文
     * @param url     网址
     */
    public static void openUrl(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
