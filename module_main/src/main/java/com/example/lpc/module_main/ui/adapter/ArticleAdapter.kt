package com.example.lpc.module_main.ui.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.module_main.R

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :HomeAdapter
 * Desc:文章
 */
class ArticleAdapter(var datas: MutableList<Article>) :
    BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article, datas) {

    var page = 0

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
        //是否是最新的
        holder.setGone(R.id.tv_refresh, !item.fresh)
        //是否置顶
        holder.setGone(R.id.tv_top, item.type == 0)
    }

    override fun addData(newData: Collection<Article>) {
        super.addData(newData)
        page++;
    }
}