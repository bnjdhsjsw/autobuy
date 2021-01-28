package com.istone.testbuy.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.istone.testbuy.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideUtil {

    private static final String GIF = "gif";

    public enum HolderType {
        DEFAULT_IMAGE, LAND_IMAGE, SQUARE_IMAGE,AVATAR_DEFAULT
    }

    private enum ResizeType {
        WIDTH, HEIGHT
    }

    public static void clearMemory(final Context context) {
        Glide.get(context).clearMemory();
    }

    private static Context getContext() {
        Activity activity = ActivityUtils.getTopActivity();
        if (activity != null) return activity;
        return Utils.getApp();
    }

    private static RequestBuilder<Bitmap> getBitmapRequestBuilder(DiskCacheStrategy strategy) {
        return Glide.with(getContext()).asBitmap()
                .dontAnimate()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .diskCacheStrategy(strategy)
                .format(DecodeFormat.DEFAULT);
    }

    private static RequestBuilder<GifDrawable> getGifDrawableRequestBuilder(DiskCacheStrategy strategy) {
        return Glide.with(getContext()).asGif()
                .dontAnimate()
                .skipMemoryCache(true)
                .diskCacheStrategy(strategy)
                .format(DecodeFormat.DEFAULT);
    }

    private static boolean isGif(String url) {
        return url.toLowerCase().contains(GIF);
    }

    private static int getMipmapFromType(HolderType type) {
        switch (type) {
            case LAND_IMAGE:
                return R.mipmap.default_image_land;
            case SQUARE_IMAGE:
                return R.mipmap.default_image_square;
            case AVATAR_DEFAULT:
                return R.mipmap.avatar_default;
            default:
                return R.mipmap.default_image;
        }
    }

    @SuppressLint("CheckResult")
    public static void loadImage(ImageView imageView, String url,
                                 DiskCacheStrategy strategy, HolderType holderType, boolean isCircle,
                                 RoundedCornersTransformation.CornerType cornerType, int corner,
                                 ResizeType resizeType) {
        url = resizeType == null ?
                ImageUrlUtil.getGlideUrl(url, imageView.getWidth(), imageView.getHeight()) :
                ImageUrlUtil.getImageUrl(url);
        if (StringUtils.isTrimEmpty(url)) return;
        RequestBuilder builder = isGif(url) ?
                getGifDrawableRequestBuilder(strategy) : getBitmapRequestBuilder(strategy);
        builder.load(url);
        if (holderType != null) {
            int resId = getMipmapFromType(holderType);
            builder.error(resId).placeholder(resId);
        }
        if (isCircle)
            builder.circleCrop();
        if (cornerType != null)
            builder.transform(new RoundedCornersTransformation(corner, 0, cornerType));
        if (resizeType != null)
            builder.into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    int bitmapWidth = resource.getWidth();
                    int bitmapHeight = resource.getHeight();
                    int imageWidth = imageView.getWidth();
                    int imageHeight = imageView.getHeight();
                    if (resizeType == ResizeType.HEIGHT)
                        imageView.getLayoutParams().height = imageWidth * bitmapHeight / bitmapWidth;
                    else
                        imageView.getLayoutParams().width = imageHeight * bitmapWidth / bitmapHeight;
                    imageView.setImageBitmap(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                    imageView.setImageDrawable(placeholder);
                }
            });
        else
            builder.into(imageView);
    }

    public static void loadImage(ImageView imageView, String url) {
        loadImage(imageView, url, DiskCacheStrategy.AUTOMATIC,
                null, false, null, 0, null);
    }

    public static void loadCircleImage(ImageView imageView, String url) {
        loadImage(imageView, url, DiskCacheStrategy.AUTOMATIC,
                null, true, null, 0, null);
    }

    public static void loadImage(ImageView imageView, String url, HolderType type) {
        loadImage(imageView, url, DiskCacheStrategy.AUTOMATIC,
                type, false, null, 0, null);
    }

    public static void loadCircleImage(ImageView imageView, String url, HolderType type) {
        loadImage(imageView, url, DiskCacheStrategy.AUTOMATIC,
                type, true, null, 0, null);
    }

    public static void loadCornerImage(ImageView imageView, String url,
                                       RoundedCornersTransformation.CornerType cornerType, int corner, HolderType type) {
        loadImage(imageView, url, DiskCacheStrategy.AUTOMATIC,
                type, false, cornerType, corner, null);
    }

    public static void loadImageNoCache(ImageView imageView, String url, HolderType type) {
        loadImage(imageView, url, DiskCacheStrategy.NONE,
                type, false, null, 0, null);
    }

    public static void loadImage(ImageView imageView, String url, HolderType type,
                                 RoundedCornersTransformation.CornerType cornerType, int corner) {
        loadImage(imageView, url, DiskCacheStrategy.AUTOMATIC, type, false, cornerType, corner, null);
    }

    public static void loadImageNoCache(ImageView imageView, String url, HolderType type,
                                        RoundedCornersTransformation.CornerType cornerType, int corner) {
        loadImage(imageView, url, DiskCacheStrategy.NONE, type, false, cornerType, corner, null);
    }

    public static void loadImageByHeight(ImageView imageView, String url, HolderType type,
                                         RoundedCornersTransformation.CornerType cornerType, int corner) {
        loadImage(imageView, url, DiskCacheStrategy.AUTOMATIC, type, false, cornerType, corner, ResizeType.WIDTH);
    }

    public static void loadImageByWidth(ImageView imageView, String url, HolderType type,
                                        RoundedCornersTransformation.CornerType cornerType, int corner) {
        loadImage(imageView, url, DiskCacheStrategy.AUTOMATIC, type, false, cornerType, corner, ResizeType.HEIGHT);
    }

    /**
     * 加载并根据返回图片尺寸重新设置imageview大学
     **/
    public static void resizeImgByPic(RequestManager requestManager,
                                      final ImageView imageView, String url) {
        SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                int w = ScreenUtils.getAppScreenWidth() - SizeUtils.dp2px(20);
                int bw = resource.getWidth();
                int bh = resource.getHeight();
                if (bw == bh) {
                    imageView.getLayoutParams().width = w;
                    imageView.getLayoutParams().height = w;
                } else {
                    // w : h = bw : bh;
                    imageView.getLayoutParams().width = w;
                    imageView.getLayoutParams().height = w * bh / bw;
                }
                imageView.setImageBitmap(resource);
            }
        };
        requestManager.asBitmap().load(url).format(DecodeFormat.PREFER_RGB_565)
                .dontTransform()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate().into(target);
    }
}
