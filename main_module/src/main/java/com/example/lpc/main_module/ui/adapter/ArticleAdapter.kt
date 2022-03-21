package com.example.lpc.main_module.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.main_module.R

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :HomeAdapter
 * Desc:文章
 */
class ArticleAdapter(var datas: MutableList<Article>) :
    BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article, datas) {


    override fun convert(holder: BaseViewHolder, item: Article) {
        holder.setText(R.id.tv_title,item.title)
    }
}