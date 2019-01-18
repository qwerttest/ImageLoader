package com.wj.base.imgloader;


/**
 * Created by yangxu on 2018/3/13.
 * <p>
 * 加载图片的参数包装类
 */

public final class XImageOptions {

    public static final int PRIORITY_HIGHT = 0X0;//优先级最高
    public static final int PRIORITY_NORMAL = 0X1;//优先级普通
    public static final int PRIORITY_LOW = 0X2;//优先级低
    public static final int STRATEGY_ALL = 0X3;// 表示缓存任何内容。
    public static final int STRATEGY_NONE = 0X4;// 表示不缓存任何内容。
    public static final int STRATEGY_RESOURCE = 0X5;//表示只缓存原始图片
    public static final int STRATEGY_CHANGED = 0X6;//表示修改过的图片


    private int placeholderId; //占位图
    private int errorId;        //加载错误占位图
    private int priority;          //加载优先级
    private int strategy;           //硬盘缓存策略
    private int overrideHeight;//指定的宽和高
    private int overrideWidth;
    private int roundDp;        //圆角图片角度
    private float thumbnail;    //缩略图的与图片源的比例
    private boolean isCircle;//原型图片
    private boolean isCacheable;//是否跳过缓存内存


    XImageOptions(Builder builder) {
        this.placeholderId = builder.placeholderId;
        this.errorId = builder.errorId;
        this.priority = builder.priority;
        this.strategy = builder.strategy;
        this.overrideHeight = builder.overrideHeight;
        this.overrideWidth = builder.overrideWidth;
        this.roundDp = builder.roundDp;
        this.thumbnail = builder.thumbnail;
        this.isCircle = builder.isCircle;
        this.isCacheable = builder.isCacheable;
    }


    public static final class Builder {

        private int placeholderId = -1;
        private int errorId = -1;
        private int priority = PRIORITY_NORMAL;
        private int strategy = STRATEGY_RESOURCE;
        private int overrideHeight = -1;
        private int overrideWidth = -1;
        private int roundDp = -1;
        private float thumbnail = -1;
        private boolean isCircle = false;
        private boolean isCacheable = false;

        public Builder() {
        }

        public Builder(XImageOptions params) {
            this.placeholderId = params.placeholderId;
            this.errorId = params.errorId;
            this.priority = params.priority;
            this.strategy = params.strategy;
            this.overrideHeight = params.overrideHeight;
            this.overrideWidth = params.overrideWidth;
            this.roundDp = params.roundDp;
            this.thumbnail = params.thumbnail;
            this.isCircle = params.isCircle;
            this.isCacheable = params.isCacheable;
        }

        public Builder error(int errorId) {
            this.errorId = errorId;
            return this;
        }

        public Builder placeholder(int placeholderId) {
            this.placeholderId = placeholderId;
            return this;
        }

        public Builder roundDp(int roundDp) {
            this.roundDp = roundDp;
            return this;
        }

        public Builder circle(boolean isCircle) {
            this.isCircle = isCircle;
            return this;
        }

        public Builder skipMemoryCache(boolean isCacheable) {
            this.isCacheable = isCacheable;
            return this;
        }

        public Builder diskCacheStrategy(int strategy) {
            this.strategy = strategy;
            return this;
        }

        public Builder override(int width, int height) {
            this.overrideWidth = width;
            this.overrideHeight = height;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder thumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public XImageOptions build() {
            return new XImageOptions(this);
        }
    }

    public Builder newBuilder() {
        return new Builder(this);
    }


    public int getPlaceholderDrawable() {
        return placeholderId;
    }

    public int getErrorPlaceholder() {
        return errorId;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCacheable() {
        return isCacheable;
    }

    public int getStrategy() {
        return strategy;
    }

    public int getOverrideHeight() {
        return overrideHeight;
    }

    public int getOverrideWidth() {
        return overrideWidth;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public int getRoundDp() {
        return roundDp;
    }

    public float getThumbnail() {
        return thumbnail;
    }
}
