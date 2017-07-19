package com.xiaolaogong.test.common.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by chechi on 2017/7/19.
 */

public final class Support {

    /**
     * 判断是否有摄像设备
     *
     * @param ctx
     * @return boolean
     */
    public static boolean hasCameraDevice(Context ctx) {
        return ctx.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    /**
     * 是否支持自动对焦
     *
     * @param camera Camera
     */
    public static boolean isAutoFocusSupported(Camera camera) {
        return camera.getParameters().getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_AUTO);
    }


    /**
     * 判断摄像设备是否支持自动聚集
     *
     * @param params
     * @return boolean
     */
    public static boolean isAutoFocusSupported(Camera.Parameters params) {
        List<String> modes = params.getSupportedFocusModes();
        return modes.contains(Camera.Parameters.FOCUS_MODE_AUTO);
    }

    /**
     * 获取相机个数,一般为两个设备,一个前置摄像,一个后置摄像
     *
     * @return int
     */
    public static int getCameraNumber() {
        return Camera.getNumberOfCameras();
    }

    /**
     * 打开摄像
     *
     * @param id 摄像ID id=0:默认为后置摄像,id=1:默认为前置摄像
     * @return Camera
     */
    public static Camera open(int id) {
        return Camera.open(id);
    }

    public static Camera.CameraInfo getInfo(int id) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(id, info);
        return info;
    }

    /**
     * 开启默认后置摄像头
     *
     * @return Camera
     */
    public static Camera openBackDefault() {
        final int numberOfCameras = Camera.getNumberOfCameras();
        int defaultCameraId = 0;
        for (int i = 0; i < numberOfCameras; i++) {
            final Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                defaultCameraId = i;
                break;
            }
        }
        return open(defaultCameraId);
    }

    /**
     * 设置相机预览方式为跟随屏幕方向
     *
     * @param context Context
     * @param camera  Camera
     */
    public static void followScreenOrientation(Context context, Camera camera) {
        final int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            camera.setDisplayOrientation(180);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            camera.setDisplayOrientation(90);
        }
    }

    public static Bitmap previewCapture(Camera camera, byte[] data) {
        final Camera.Parameters parameters = camera.getParameters();
        final int width = parameters.getPreviewSize().width;
        final int height = parameters.getPreviewSize().height;
        final YuvImage yuv = new YuvImage(data, parameters.getPreviewFormat(), width, height, null);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        yuv.compressToJpeg(new Rect(0, 0, width, height), 100, out);
        final byte[] bytes = out.toByteArray();
        final Bitmap src = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        final Matrix matrix = new Matrix();
        matrix.setRotate(90);
        final int originWidth = src.getWidth();
        final int originHeight = src.getHeight();
        final int targetWH = originWidth > originHeight ? originHeight : originWidth;
        final int offsetX = originWidth > originHeight ? (originWidth - originHeight) : 0;
        final int offsetY = originWidth > originHeight ? 0 : (originHeight - originWidth);
        return Bitmap.createBitmap(src, offsetX, offsetY, targetWH, targetWH, matrix, true);
    }


}
