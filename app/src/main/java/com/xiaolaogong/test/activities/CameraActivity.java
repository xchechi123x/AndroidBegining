package com.xiaolaogong.test.activities;

import android.hardware.Camera;
import android.os.Bundle;
import android.widget.Button;

import com.xiaolaogong.test.R;
import com.xiaolaogong.test.common.camera.LiveCameraView;
import com.xiaolaogong.test.common.tasks.SavePicAsyncTask;
import com.xiaolaogong.test.common.tools.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chechi on 2017/7/19.
 */

public class CameraActivity extends BaseActivity {

    @BindView(R.id.liveCameraView)
    LiveCameraView liveCameraView;

    @BindView(R.id.takePicture)
    Button takePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.takePicture)
    void takePicture() {
        liveCameraView.getCamera().takePicture(null, null, new TakePictureCallBack());
    }

    /**
     * 保存拍照图片回调实现
     */
    private final class TakePictureCallBack implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            SavePicAsyncTask task = new SavePicAsyncTask("xiaolaogong", Tools.getStringTimestamp(), SavePicAsyncTask.SAVE_TYPE_PNG, data);
            task.execute();
            camera.startPreview();
        }
    }

}
