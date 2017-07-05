package com.xiaolaogong.test.net.requests;

import com.xiaolaogong.test.net.base.Response;
import com.xiaolaogong.test.net.base.Router;
import com.xiaolaogong.test.net.resolve.user.User;

import retrofit2.http.GET;
import io.reactivex.Observable;

/**
 * Created by chechi on 2017/7/4.
 */

public interface UserRequest {

    @GET(Router.User.GET_USER)
    Observable<Response<User>> getUser();
}
