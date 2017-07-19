package com.xiaolaogong.test.common.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

/**
 * Created by chechi on 2017/7/19.
 */

public class LiveCameraView extends CameraPreviewView {

    private static final String TAG = LiveCameraView.class.getSimpleName();

    public LiveCameraView(Context context) {
        super(context);
    }

    public LiveCameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LiveCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private CaptureCallback captureCallback;

    private final Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            Log.i(TAG, "-> Got preview frame data");
            captureCallback.onCaptured(Support.previewCapture(camera, data));
        }
    };

    private final DelayedFocusLooper focusLooper = new DelayedFocusLooper() {

        private final Camera.AutoFocusCallback handler = new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.setOneShotPreviewCallback(previewCallback);
                } else {
                    Log.w(TAG, "-> Request focus, but fail !");
                }
            }
        };

        @Override
        public void callAutoFocus() {
            camera.autoFocus(handler);
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Support.openBackDefault();
        if (camera != null) {
            setCamera(camera);
        }
        super.surfaceCreated(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);
        focusLooper.stop();
        if (camera != null) {
            camera.release();
        }
    }

    /**
     * 启动自动对焦拍摄
     *
     * @param delay           每次聚焦的延时时间，单位：毫秒
     * @param captureCallback 聚焦后的拍摄图片回调接口
     */
    public void startAutoCapture(int delay, CaptureCallback captureCallback) {
        this.captureCallback = captureCallback;
        if (camera != null) {
            focusLooper.start(delay);
        } else {
            Toast.makeText(getContext(), "OPEN CAMERA FAIL", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 停止自动对焦拍摄
     */
    public void stopAutoCapture() {
        focusLooper.stop();
    }

    /**
     * @return boolean 返回当前设备是否支持自动对焦拍摄功能
     */
    public boolean isAutoCaptureSupported() {
        return Support.isAutoFocusSupported(camera);
    }
}
