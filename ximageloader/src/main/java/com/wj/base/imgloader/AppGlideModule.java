package com.wj.base.imgloader;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by yangxu on 2018/3/14.
 */

public class AppGlideModule implements GlideModule {

    public static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;


    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE));
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);

    }

    /**
     * 添加进度监听拦截器
     */
    @Override
    public void registerComponents(Context context, Glide glide) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ProgressInterceptor())
                .build();
        glide.register(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory(client));

    }

}
