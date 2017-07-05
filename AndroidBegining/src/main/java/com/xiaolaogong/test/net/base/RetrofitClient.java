package com.xiaolaogong.test.net.base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chechi on 2017/3/6.
 */

public class RetrofitClient {

    private Retrofit.Builder builder;

    private String baseUrl;

    private boolean setClient = false;

    private RetrofitClient() {
    }

    public RetrofitClient(Retrofit.Builder builder) {
        this.builder = builder;
    }

    private void checkBaseUrl() {
        if (this.baseUrl == null) {
            throw new IllegalStateException("BASE_URI MUST SET!");
        }
    }

    public RetrofitClient setBaseUri(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public static RetrofitClient getInstance() {
        return new RetrofitClient(new Retrofit.Builder());
    }


    public RetrofitClient setClient(OkHttpClient client) {
        checkBaseUrl();
        this.setClient = true;
        this.builder.client(client);
        return this;
    }

    public Retrofit create() {
        if (!setClient) {
            throw new IllegalStateException("OKHTPP CLIENT MUST SET!");
        }
        this.builder.baseUrl(this.baseUrl);
        this.builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        this.builder.addConverterFactory(GsonConverterFactory.create());

        return this.builder.build();
    }

}
