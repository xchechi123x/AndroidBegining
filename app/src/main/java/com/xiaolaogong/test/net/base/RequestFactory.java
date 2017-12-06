package com.xiaolaogong.test.net.base;

import com.xiaolaogong.test.AppApplication;
import com.xiaolaogong.test.net.interceptors.LogInterceptor;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by chechi on 2017/3/4.
 */

public final class RequestFactory {

    private static RequestFactory instance;

    private Retrofit retrofit;

    private OkHttpClient httpClient;

    private static Cache getCache() {
        Cache cache = new Cache(AppApplication.instance.getCacheDir(), 1024 * 1024 * 10);
        return cache;
    }

    private RequestFactory(String baseUri, Cache cache) {

        httpClient = HttpClient.getInstance().
                setCache(cache).
                addInterceptor(new LogInterceptor()).
                createClient();

        this.retrofit = RetrofitClient.getInstance().
                setBaseUri(baseUri).
                setClient(httpClient).
                create();
    }

    public void rebuild(String baseUri) {
        this.retrofit = RetrofitClient.getInstance().
                setBaseUri(baseUri).
                setClient(httpClient).
                create();
    }

    public static RequestFactory getInstance(String baseUri) {

        if (instance == null) {
            return new RequestFactory(baseUri, getCache());
        }

        return instance;
    }

    public <T> T createRequest(Class<T> model) {
        return this.retrofit.create(model);
    }
}
