package com.xiaolaogong.test.net.base;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by chechi on 2017/3/6.
 */

public class HttpClient {

    private OkHttpClient.Builder builder;

    private HttpClient() {

    }

    public HttpClient(OkHttpClient.Builder builder) {
        this.builder = builder;
    }

    public static HttpClient getInstance() {
        return new HttpClient(new OkHttpClient.Builder());
    }

    public HttpClient addInterceptor(Interceptor interceptor) {
        this.builder.addInterceptor(interceptor);
        return this;
    }

    public HttpClient setCache(Cache cache) {
        this.builder.cache(cache);
        return this;
    }

    public OkHttpClient createClient() {

        this.builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        return builder.build();
    }
}
