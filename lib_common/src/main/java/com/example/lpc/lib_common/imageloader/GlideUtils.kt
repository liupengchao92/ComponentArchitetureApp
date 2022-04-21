package com.example.lpc.lib_common.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lpc.lib_common.R

/**
 * Author: liupengchao
 * Date: 2022/3/22
 * ClassName :GlideUtils
 * Desc:
 */
object GlideUtils {


    fun loadImage(url: String, imageView: ImageView, roundingRadius: Int = 0) {
        if (roundingRadius > 0) {
            Glide
                .with(imageView.context)
                .load(url)
                .error(R.drawable.default_project_img)
                .placeholder(R.drawable.default_project_img)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(roundingRadius)))
                .into(imageView);
        } else {
            Glide
                .with(imageView.context)
                .load(url)
                .error(R.drawable.default_project_img)
                .placeholder(R.drawable.default_project_img)
                .into(imageView);
        }
    }
}