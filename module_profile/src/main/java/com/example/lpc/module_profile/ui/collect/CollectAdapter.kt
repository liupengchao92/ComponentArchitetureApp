package com.example.lpc.module_profile.ui.collect

import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.module_profile.R

/**
 * Author: liupengchao
 * Date: 2022/6/14
 * ClassName :CollectAdapter
 * Desc:
 */
class CollectAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_collect) {

    var currentPage = 0;

    override fun convert(holder: BaseViewHolder, item: Article) {
        //标题
        holder.setText(R.id.tv_title, item.title)
        //副描述
        val stringBuilder = StringBuilder().apply {
            //作者
            if (!TextUtils.isEmpty(item.author)) {
                append("作者:${item.author}")
            }
            //分享者
            if (!TextUtils.isEmpty(item.shareUser)) {
                append("分享人:${item.shareUser}")
            }
            //
            append(" 分类:${item.superChapterName}")

            //时间
            append("  ${item.niceDate}")
        }

        //时间
        holder.setText(R.id.tv_date, stringBuilder.toString())

        //点击事件
        holder.getView<View>(R.id.itemView).setOnClickListener {

            ARouter.getInstance()
                .build(ARouterConstant.Common.WEB)
                .withSerializable(ParamsKeyConstant.ARTICLE,item)
                .navigation()
        }
    }
}