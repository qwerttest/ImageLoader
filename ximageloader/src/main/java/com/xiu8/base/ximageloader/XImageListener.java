package com.xiu8.base.ximageloader;

import android.graphics.drawable.Drawable;

/**
 * Created by yangxu on 2018/4/2.
 */

public interface XImageListener {

    void onStart();

    void onStop();

    void onLoadFailed(Drawable errorDrawable);

    void onProgress(int progress);

    boolean onDrawableReady(Drawable drawable);
}
