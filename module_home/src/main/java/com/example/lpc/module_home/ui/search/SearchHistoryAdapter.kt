package com.example.lpc.module_home.ui.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.lpc.lib_common.database.entity.KeyWord
import com.example.lpc.module_home.R

/**
 * Author: liupengchao
 * Date: 2022/4/11
 * ClassName :SearchHistoryAdapter
 * Desc:
 */
class SearchHistoryAdapter(datas: MutableList<KeyWord>) :
    BaseQuickAdapter<KeyWord, BaseViewHolder>(R.layout.item_search_history, datas) {

    init {
        //添加点击事件
        addChildClickViewIds(R.id.deleteIv)
    }

    override fun convert(holder: BaseViewHolder, item: KeyWord) {

        holder.setText(R.id.keywordTv, item.keyWord)

    }
}