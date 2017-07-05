package com.xiaolaogong.test.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xiaolaogong.test.R;
import com.xiaolaogong.test.net.base.ResponseObserver;
import com.xiaolaogong.test.net.base.Response;
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
        getUser();

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
                        Log.d(TAG, "request:" + response.data.name);
                        Log.d(TAG, "request:" + response.data.password);
                        textView.setText(response.data.name);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "request:" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "request:Complete" );
                    }
                });
    }

    @OnClick(R.id.text)
    void textViewOnclick() {
        Toast.makeText(getApplicationContext(), textView.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
