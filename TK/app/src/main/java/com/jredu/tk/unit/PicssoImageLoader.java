package com.jredu.tk.unit;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

import java.io.File;

/**
 * Created by HunBing on 2016/11/2.
 * 控制图片轮播下载图片
 */
public class PicssoImageLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((int)path).into(imageView);
    }
}
