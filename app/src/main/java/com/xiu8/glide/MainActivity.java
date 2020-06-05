package com.xiu8.glide;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wj.base.imgloader.XImageLoader;
import com.wj.base.imgloader.XImageOptions;
import com.wj.base.imgloader.XImagePreloadListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private Button mBtn;
    private ImageView mAvatar,mErrorAvator;

    private static final String url = "http://p0.so.qhimgs1.com/bdr/_240_/t01ccdbef5e84c61d8a.jpg";
//  private static final String url = "http://guolin.tech/test.gif";
//  private static final String url = "http://asgard.image.mucang.cn/asgard/2017/12/28/10/45ab2f68a13546c1914b61d54160b8ae.webp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setEvent();
        XImageLoader.getInstance().init(getApplication(),new XImageOptions.Builder().build());
       
    }

    private void initView() {
        mBtn= (Button) findViewById(R.id.btn);
        mAvatar= (ImageView) findViewById(R.id.image);
        mErrorAvator= (ImageView) findViewById(R.id.image2);
    }

    private void setEvent() {
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                XImageOptions options=new XImageOptions.Builder().thumbnail(0.1f).skipMemoryCache(true).placeholder(R.drawable.plachor).error(R.drawable.error).diskCacheStrategy(XImageOptions.STRATEGY_NONE).circle(true).build();
           /*     XImageLoader.getInstance().display(mAvatar, url, options, new XImageListener() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart");
                    }

                    @Override
                    public void onStop() {
                        Log.i(TAG, "onStop");
                    }

                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        mAvatar.setImageResource(R.drawable.plachor);
                    }

                    @Override
                    public void onProgress(int progress) {
                        Log.i(TAG, "onProgress"+progress);
                    }

                    @Override
                    public boolean onDrawableReady(Drawable drawable) {
                        Log.i(TAG, "onDrawableReady");
                        return false;
                    }
                });*/
           XImageLoader.getInstance().preLoading(url,true, new XImagePreloadListener() {

               @Override
               public void onDrawableReady(Drawable drawable) {
                   mErrorAvator.setImageDrawable(drawable);
                   Log.i(TAG, "onResourceReady: macti"+XImageLoader.getInstance().getDiskCache());
               }
           });
              /*  XImageLoader.getInstance().downloadOnly(url, new XImageFileListener() {
                    @Override
                    public void onFileReady(File file) {
                        Log.i(TAG, "onResourceReady: macti"+file.getAbsolutePath());
                    }
                });*/
                break;
        }
    }
}
