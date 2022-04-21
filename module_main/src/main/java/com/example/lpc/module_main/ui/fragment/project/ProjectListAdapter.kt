package com.example.lpc.module_main.ui.fragment.project

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.imageloader.GlideUtils
import com.example.lpc.module_main.R

/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ProjectListAdapter
 * Desc:
 */
class ProjectListAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_project) {

    init {

        addChildClickViewIds(R.id.itemView, R.id.iv_favorite)
    }

    override fun convert(holder: BaseViewHolder, item: Article) {

        holder.setText(R.id.titleTv, item.title)

        holder.setText(R.id.descTv, item.desc)

        holder.setText(R.id.dateDescTv, item.niceDate)

        item.envelopePic?.let {
            GlideUtils.loadImage(it, holder.getView(R.id.coverIv))
        }
    }
}