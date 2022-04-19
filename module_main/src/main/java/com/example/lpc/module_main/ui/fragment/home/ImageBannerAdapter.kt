package com.example.lpc.module_main.ui.fragment.home

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SizeUtils
import com.example.lpc.lib_common.http.pojo.Banner
import com.example.lpc.lib_common.imageloader.GlideUtils
import com.youth.banner.adapter.BannerAdapter


/**
 * Author: liupengchao
 * Date: 2022/3/22
 * ClassName :BannerAdapter
 * Desc:轮播图适配器
 */
class ImageBannerAdapter(var images: MutableList<Banner>) :
    BannerAdapter<Banner, BannerViewHolder>(images) {


    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {

        val imageView = ImageView(parent?.context).apply {

            scaleType = ImageView.ScaleType.CENTER_CROP

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: Banner?, position: Int, size: Int) {
        data?.let {
            GlideUtils.loadImage(it.imagePath, holder!!.imageView, SizeUtils.dp2px(5f))
        }
    }


}

class BannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView)
