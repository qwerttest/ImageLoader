package com.wj.base.imgloader;

import android.app.Activity;
import android.os.Build;

/**
 * 描述：图片加载工具类
 * created by wty on 2020/5/5 6:06 PM.
 */
public class XImageUtils {

    public static boolean isActive(Activity aActivity) {
        return aActivity != null && !aActivity.isFinishing() && (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1 || !aActivity.isDestroyed());
    }
}
