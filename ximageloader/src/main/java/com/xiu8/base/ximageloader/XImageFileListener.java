package com.xiu8.base.ximageloader;


import java.io.File;

/**
 * Created by yangxu on 2018/4/3.
 * 用于下载图片之后的路径回调
 */

public interface XImageFileListener {

    void onFileReady(File file);
}
