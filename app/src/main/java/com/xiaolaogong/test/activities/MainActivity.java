package com.xiaolaogong.test.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.common.logging.FLog;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xiaolaogong.test.R;
import com.xiaolaogong.test.net.base.Response;
import com.xiaolaogong.test.net.base.ResponseObserver;
import com.xiaolaogong.test.net.requests.UserRequest;
import com.xiaolaogong.test.net.resolve.user.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class MainActivity extends BaseActivity {

    @BindView(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //申请授权
        requestPermission();
    }

    /**
     * 动态申请权限相应
     */
    private void requestPermission() {
        permissions
                .requestEach(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        if (permission.name.equals(Manifest.permission.READ_PHONE_STATE)) {
                        }
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        if (permission.name.equals(Manifest.permission.READ_PHONE_STATE)) {
                        }
                    } else {
                        if (permission.name.equals(Manifest.permission.READ_PHONE_STATE)) {
                        }
                    }
                });

    }

    private void getUser() {
        createRequest(UserRequest.class).
                getUser().
                compose(MainActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(new ResponseObserver<Response<User>>() {

                    @Override
                    public void onNext(@NonNull Response<User> response) {
                        FLog.d(TAG, "request:" + response.data.name);
                        FLog.d(TAG, "request:" + response.data.password);
                        textView.setText(response.data.name);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "request:" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "request:Complete");
                    }
                });
    }

    @OnClick(R.id.text)
    void textViewOnclick() {
        startActivity(new Intent(MainActivity.this, CameraActivity.class));
    }

    @OnClick(R.id.goUser)
    void goUser() {
        startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
    }

    @OnClick(R.id.test_view)
    void test() {
        startActivity(new Intent(MainActivity.this, TestActivity.class));
    }

    @OnClick(R.id.user_goods)
    void userGoods() {
        startActivity(new Intent(MainActivity.this, ActivityUserGoods.class));
    }

}
