package com.xtjc.pictureselector;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class ShowImageUtils {
    /**
     * glide配置（图片裁剪）
     *
     * @param loadingImg 加载时的图片
     * @return
     */
    public static RequestOptions getRequestOptionsCenterCrop(int loadingImg) {
        return new RequestOptions()
                .placeholder(loadingImg)// 正在加载中的图片
                .error(loadingImg) // 加载失败的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().centerCrop(); // 磁盘缓存策略
    }

    /**
     * glide配置（图片裁剪）
     *
     * @param loadingImg 加载时的图片
     * @return
     */
    public static RequestOptions getRequestOptionsCenterInside(int loadingImg) {
        return new RequestOptions()
                .placeholder(loadingImg)// 正在加载中的图片
                .error(loadingImg) // 加载失败的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().centerInside(); // 磁盘缓存策略
    }

    /**
     * glide配置（图片等比缩放）
     *
     * @param loadingImg 加载时的图片
     * @return
     */
    public static RequestOptions getRequestOptions(int loadingImg) {
        return new RequestOptions()
                .placeholder(loadingImg)// 正在加载中的图片
                .error(loadingImg) // 加载失败的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate(); // 磁盘缓存策略
    }

    /**
     * 加载图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void showImage(Context context, String imageUrl, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(imageUrl)
                    .apply(getRequestOptionsCenterCrop(R.mipmap.bg_load_defalu))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 加载图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void showImageCenterInside(Context context, String imageUrl, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(imageUrl)
                    .apply(getRequestOptionsCenterInside(R.mipmap.bg_load_defalu))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 加载图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void showImageFitCenter(Context context, String imageUrl, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(imageUrl)
                    .apply(getRequestOptions(R.mipmap.bg_load_defalu))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载图片自定义加载时页面
     *
     * @param context    上下文
     * @param imageUrl   图片Url
     * @param imageView  控件
     * @param loadingImg 加载时显示的图片
     */
    public static void showImage(Context context, String imageUrl, ImageView imageView, int loadingImg) {
        try {
            Glide.with(context)
                    .load(imageUrl)
                    .apply(getRequestOptions(loadingImg))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 加载gif图片
     *
     * @param context
     * @param imgUrl    gif id
     * @param imageView
     */
    public static void showGifORPngImage(Context context, String imgUrl, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(imgUrl)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载本地gif图片
     *
     * @param context
     * @param imgId
     * @param imageView
     */
    public static void showGIFImage(Context context, int imgId, ImageView imageView) {
        try {
            Glide.with(context)
                    .asGif()
                    .load(imgId)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
