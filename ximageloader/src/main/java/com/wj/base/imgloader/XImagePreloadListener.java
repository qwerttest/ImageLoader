package com.wj.base.imgloader;

import android.graphics.drawable.Drawable;


/**
 * Created by yangxu on 2018/4/2.
 *
 * 用于预加载回调
 */

public interface XImagePreloadListener {

    void onDrawableReady(Drawable drawable);
}
