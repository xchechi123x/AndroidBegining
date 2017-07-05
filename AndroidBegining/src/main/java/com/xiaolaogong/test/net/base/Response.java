package com.xiaolaogong.test.net.base;

/**
 * Created by chechi on 2017/3/6.
 */

public class Response<T> {

    public String message;
    public int code;
    public T data;

    @Override
    public String toString() {
        return "Response{" +
                "message=" + message +
                ", code=" + code +
                ", data=" + data.toString() +
                '}';
    }
}
