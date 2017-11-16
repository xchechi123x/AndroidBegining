package com.xiaolaogong.test.common.tasks;

import android.os.AsyncTask;

import com.xiaolaogong.test.common.camera.Support;

import java.io.IOException;

/**
 * Created by chechi on 2017/7/21.
 */

public class SavePicAsyncTask extends AsyncTask<Void, Void, String> {

    public static final int SAVE_TYPE_PNG = 0;

    public static final int SAVE_TYPE_JPG = 1;

    public static final String EXT_PNG = ".png";

    public static final String EXT_JPEG = ".jpeg";

    private byte[] data;

    private String dir;

    private String name;

    private int saveType;

    public SavePicAsyncTask(String dir, String name, int saveType, byte[] data) {
        this.data = data;
        this.dir = dir;
        this.name = name;
        this.saveType = saveType;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            Support.saveTakePicture(dir, name, saveType, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
