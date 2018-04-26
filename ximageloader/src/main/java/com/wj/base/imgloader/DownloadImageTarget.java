package com.wj.base.imgloader;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * Created by yangxu on 2018/4/3.
 */

public class DownloadImageTarget implements Target<File> {

    private XImageFileListener mListener;

    public DownloadImageTarget(XImageFileListener listener) {
        this.mListener= listener;
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {

    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {

    }

    @Override
    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
        if (mListener!=null){
            mListener.onFileReady(resource);
        }
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {

    }

    @Override
    public void getSize(SizeReadyCallback cb) {
        cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    @Override
    public void setRequest(Request request) {

    }

    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
