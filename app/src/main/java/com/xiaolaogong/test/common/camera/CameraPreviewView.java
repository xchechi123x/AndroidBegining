package com.xiaolaogong.test.common.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by chechi on 2017/7/19.
 */

public class CameraPreviewView extends SurfaceView implements SurfaceHolder.Callback {

    private final static String TAG = CameraPreviewView.class.getSimpleName();

    private SurfaceHolder surfaceHolder;

    private PreviewReadyCallback previewReadyCallback;

    protected Camera camera;

    public Camera getCamera() {
        return camera;
    }


    public CameraPreviewView(Context context) {
        super(context);
        init();
    }

    public CameraPreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CameraPreviewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "Start preview display[SURFACE-CREATED]");
        startPreviewDisplay(surfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        Support.followScreenOrientation(getContext(), camera);
        Log.d(TAG, "Restart preview display[SURFACE-CHANGED]");
        stopPreviewDisplay();
        startPreviewDisplay(surfaceHolder);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
        checkCamera();
//        final Camera.Parameters params = this.camera.getParameters();
//        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
//        params.setSceneMode(Camera.Parameters.SCENE_MODE_BARCODE);
    }

    public void setPreviewReadyCallback(PreviewReadyCallback previewCallback) {
        previewReadyCallback = previewCallback;
    }

    /**
     * 开启相机预览
     *
     * @param surfaceHolder
     */
    private void startPreviewDisplay(SurfaceHolder surfaceHolder) {
        checkCamera();
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            Log.e(TAG, "Error while START preview for camera", e);
        }

        //预览开始监听回调
        if (previewReadyCallback != null) {
            previewReadyCallback.onStarted(camera);
        }
    }

    /**
     * 停止相机预览
     */
    private void stopPreviewDisplay() {
        checkCamera();
        try {
            camera.stopPreview();
        } catch (Exception e) {
            Log.e(TAG, "Error while STOP preview for camera", e);
        }

        //预览时间监听回调
        if (previewReadyCallback != null) {
            previewReadyCallback.onStopped();
        }
    }

    private void checkCamera() {
        if (camera == null) {
            throw new IllegalStateException("Camera must be set when start/stop preview, call <setCamera(Camera)> to set");
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Stop preview display[SURFACE-DESTROYED]");
        stopPreviewDisplay();
    }

    public interface PreviewReadyCallback {
        /**
         * 预览功能已准备完成
         *
         * @param camera 相机对象
         */
        void onStarted(Camera camera);

        /**
         * 预览功能已停止
         */
        void onStopped();
    }

}
