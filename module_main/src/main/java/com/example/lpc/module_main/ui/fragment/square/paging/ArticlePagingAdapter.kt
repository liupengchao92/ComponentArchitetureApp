package com.example.lpc.module_main.ui.fragment.square.paging

import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.module_main.R
import com.example.lpc.module_main.ui.activity.ui.web.CommonWebActivity

/**
 * Author: liupengchao
 * Date: 2022/4/19
 * ClassName :SquarePagingAdapter
 * Desc:
 */
class ArticlePagingAdapter : PagingDataAdapter<Article, BaseViewHolder>(diffCallback) {

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<Article>() {

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {

                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {

                return oldItem.type == newItem.type
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return BaseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        getItem(position)?.let { item ->
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
            //是否收藏
            holder.setImageResource(
                R.id.iv_favorite,
                if (item.collect) R.drawable.ic_like else R.drawable.ic_un_like
            )
            //时间
            holder.setText(R.id.tv_date, stringBuilder.toString())
            //是否是最新的
            holder.setGone(R.id.tv_refresh, !item.fresh)
            //是否置顶
            holder.setGone(R.id.tv_top, item.type == 0)
            //标签
            if (!item.tags.isNullOrEmpty()) {
                holder.setGone(R.id.tv_tags, false)
                val stringBuilder = StringBuilder()
                item.tags?.forEach {
                    stringBuilder.append(it.name).append(" · ")
                }
                holder.setText(
                    R.id.tv_tags,
                    stringBuilder.substring(0, stringBuilder.lastIndex - 1)
                )
            } else {
                holder.setGone(R.id.tv_tags, true)
            }
            //点击事件
            holder.getView<View>(R.id.itemView).setOnClickListener {
                val intent = Intent(ActivityUtils.getTopActivity(), CommonWebActivity::class.java)
                intent.putExtra(ParamsKeyConstant.ARTICLE, item)
                ActivityUtils.getTopActivity().startActivity(intent)
            }
        }
    }
}
