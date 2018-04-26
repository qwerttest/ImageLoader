package com.xiu8.base.ximageloader;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by yangxu on 2018/3/20.
 * 用来创建okhttp对象
 */

public class OkhttpFactory {

    private static final long CONNECT = 5000;
    private static final long READ = 5000;
    private static final long WRITE = 5000;

    private OkHttpClient createClient(){
        OkHttpClient.Builder  client =new OkHttpClient.Builder();
        client.connectTimeout(CONNECT, TimeUnit.MILLISECONDS)
                .readTimeout(READ, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE, TimeUnit.MILLISECONDS);
        return client.build();
    }
}
