package com.xiaolaogong.test.common.camera;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by chechi on 2017/7/19.
 */

public abstract class DelayedFocusLooper {

    public static final String TAG = DelayedFocusLooper.class.getSimpleName();

    private static final int MSG_FOCUS = 999;

    private boolean canNextFocus = false;

    private int period = 1000;

    private final Handler delayedHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.i(TAG, "-> Call auto focus");
            callAutoFocus();
            if (canNextFocus) {
                sendNextAutoFocus(period);
            }
            return true;
        }
    });


    public void start(int period) {
        canNextFocus = true;
        this.period = Math.abs(period);
        Log.i(TAG, "-> Start auto focus with period: " + period);
        sendNextAutoFocus(0/*first: no delay*/);
    }

    public void stop() {
        Log.i(TAG, "-> Stop auto focus");
        canNextFocus = false;
        delayedHandler.removeMessages(MSG_FOCUS);
    }

    private void sendNextAutoFocus(int period) {
        if (period == 0) {
            delayedHandler.sendEmptyMessage(MSG_FOCUS);
        } else {
            delayedHandler.sendEmptyMessageDelayed(MSG_FOCUS, period);
        }
    }

    public abstract void callAutoFocus();
}

