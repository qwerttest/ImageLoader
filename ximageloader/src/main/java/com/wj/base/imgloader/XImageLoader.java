package com.wj.base.imgloader;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;


/**
 * Created by yangxu on 2018/3/13.
 * <p>
 * 基于Glide 3.7 版本
 * 图片工具
 */

public final class XImageLoader implements IXImage {

    private XImageOptions defaultParams;
    private Application application;
    private static final String TAG = "XImageLoader";

    private XImageLoader() {
    }

    private final static class Holder {
        public final static XImageLoader imageLoader = new XImageLoader();
    }


    public static XImageLoader getInstance() {
        return Holder.imageLoader;
    }

    @Override
    public void init(Application application, XImageOptions defaultParams) {
        this.application = application;
        this.defaultParams = defaultParams;
    }

    @Override
    public void display(ImageView imageView, String url, XImageListener listener) {
        display(imageView, url, defaultParams, listener);
    }

    @Override
    public void display(ImageView imageView, String url, XImageOptions params, XImageListener listener) {
        configDisplay(imageView, url, params, listener);
    }

    @Override
    public void display(ImageView imageView, String url) {
        display(imageView, url, defaultParams, null);
    }

    @Override
    public void display(ImageView imageView, File file) {
        display(imageView, file, defaultParams);
    }

    @Override
    public void display(ImageView imageView, Uri uri) {
        display(imageView, uri, defaultParams);
    }

    @Override
    public void display(ImageView imageView, Integer integer) {
        display(imageView, integer, defaultParams);
    }

    @Override
    public void display(ImageView imageView, byte[] model) {
        display(imageView, model, defaultParams);
    }

    @Override
    public void display(ImageView imageView, String url, XImageOptions params) {
        configDisplay(imageView, url, params, null);
    }

    @Override
    public void display(ImageView imageView, File file, XImageOptions params) {
        configDisplay(imageView, file, params, null);
    }

    @Override
    public void display(ImageView imageView, Uri uri, XImageOptions params) {
        configDisplay(imageView, uri, params, null);
    }

    @Override
    public void display(ImageView imageView, Integer resId, XImageOptions params) {
        configDisplay(imageView, resId, params, null);
    }

    @Override
    public void display(ImageView imageView, byte[] model, XImageOptions params) {
        configDisplay(imageView, model, params, null);
    }

    /**
     * 使用此方法 必须把缓存策略设置为DiskCacheStrategy.SOURCE
     */
    public void preLoading(String url,boolean isCaCheMemory, XImagePreloadListener listener) {
        preLoadResource(url, isCaCheMemory,listener);
    }

    @Override
    public String getDiskCache() {
        String disCache = application.getExternalCacheDir() + ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
        return disCache;
    }

    @Override
    public void downloadOnly(String url,XImageFileListener xImageFileListener) {
        Glide.with(application).load(url).downloadOnly(new DownloadImageTarget(xImageFileListener));
    }

    private <T> void configDisplay(ImageView imageView, final T url, XImageOptions params, final XImageListener listener) {
        //没有初始化 则抛出异常
        if (defaultParams == null) {
            throw new RuntimeException("没有初始化");
        }
        Context context = imageView.getContext();
        if(context == null){
            return;
        }
        if(context instanceof Activity){
            if(((Activity)context).isFinishing()){
                return;
            }
        }

        DrawableTypeRequest<T> tDrawableTypeRequest = setOptions(Glide.with(context).load(url), params);
        if (url instanceof String) {
            final String key = (String) url;
            tDrawableTypeRequest.into(new GlideDrawableImageViewTarget(imageView) {
                //如果设置了缩略图,该方法会被回调两次
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    Log.i(TAG, "onResourceReady");
                    ProgressInterceptor.removeListener(key);
                    if (listener == null || !listener.onDrawableReady(resource)) {
                        super.onResourceReady(resource, animation);
                    }
                }


                @Override
                public void onStart() {
                    if (listener != null) {
                        listener.onStart();
                    }
                    ProgressInterceptor.addListener(key, new ProgressListener() {
                        @Override
                        public void onProgress(int progress) {
                            if (listener != null) {
                                listener.onProgress(progress);
                            }
                        }
                    });
                    super.onStart();
                }

                @Override
                public void onStop() {
                    ProgressInterceptor.removeListener(key);
                    if (listener != null) {
                        listener.onStop();
                    }
                    super.onStop();
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    ProgressInterceptor.removeListener(key);
                    if (listener != null) {
                        listener.onLoadFailed(errorDrawable);
                    }
                    super.onLoadFailed(e, errorDrawable);
                }
            });
        } else {
            tDrawableTypeRequest.into(imageView);
        }

    }

    private <T> DrawableTypeRequest<T> setOptions(DrawableTypeRequest<T> options, XImageOptions params) {
        /**
         * 把默认的参数从封装的PictureParams中提取出来,填充到框架参数中
         * */
        int errorPlaceholder = params.getErrorPlaceholder();
        if (errorPlaceholder != -1) {
            options.error(errorPlaceholder);
        }
        int placeholderDrawable = params.getPlaceholderDrawable();
        if (errorPlaceholder != -1) {
            options.placeholder(placeholderDrawable);
        }
        int overrideWidth = params.getOverrideWidth();
        int overrideHeight = params.getOverrideHeight();
        if (overrideWidth != -1 || overrideHeight != -1) {
            options.override(overrideWidth, overrideHeight);
        }
        int priority = params.getPriority();
        if (priority == XImageOptions.PRIORITY_HIGHT) {
            options.priority(Priority.HIGH);
        } else if (priority == XImageOptions.PRIORITY_NORMAL) {
            options.priority(Priority.NORMAL);
        } else if (priority == XImageOptions.PRIORITY_LOW) {
            options.priority(Priority.LOW);
        }
        int strategy = params.getStrategy();
        if (strategy == XImageOptions.STRATEGY_ALL) {
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else if (strategy == XImageOptions.STRATEGY_NONE) {
            options.diskCacheStrategy(DiskCacheStrategy.NONE);
        } else if (strategy == XImageOptions.STRATEGY_RESOURCE) {
            options.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        } else if (strategy == XImageOptions.STRATEGY_CHANGED) {
            options.diskCacheStrategy(DiskCacheStrategy.RESULT);
        }
        float thumbnail = params.getThumbnail();
        if (thumbnail != -1) {
            options.thumbnail(thumbnail);
        }
        int dp = params.getRoundDp();
        if (dp != -1) {
            options.transform(new GlideRoundTransform(application, dp));
        }
        boolean circle = params.isCircle();
        if (circle) {
            options.transform(new GlideCircleTransform(application));
        }
        options.skipMemoryCache(params.isCacheable());
        options.dontAnimate();
        return options;
    }

    private void preLoadResource(String url,boolean isCaCheMemory ,final XImagePreloadListener listener) {
        Glide.with(application).load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(!isCaCheMemory)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (listener!=null){
                            listener.onDrawableReady(resource);
                        }
                        return false;
                    }
                }).preload();
    }
}
