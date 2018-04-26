package com.xiu8.base.ximageloader;

import android.app.Application;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by yangxu on 2018/3/13.
 *
 * 图片框架的顶级接口
 */

public interface IXImage {

    void init(Application application, XImageOptions defaultParams);

    void display(ImageView imageView, String url);

    void display(ImageView imageView, String url, XImageOptions params);

    void display(ImageView imageView, String url,XImageListener listener);

    void display(ImageView imageView, String url, XImageOptions params,XImageListener listener);

    void display(ImageView imageView, File file);

    void display(ImageView imageView, File file, XImageOptions params);

    void display(ImageView imageView, Uri uri);

    void display(ImageView imageView, Uri uri, XImageOptions params);

    void display(ImageView imageView, Integer resId);

    void display(ImageView imageView, Integer resId, XImageOptions params);

    void display(ImageView imageView, byte[] model);

    void display(ImageView imageView, byte[] model, XImageOptions params);

    void preLoading(String url,boolean isCacheMemory,XImagePreloadListener listener);

    String getDiskCache();

    void downloadOnly(String url,XImageFileListener xImageFileListener);

}
