package com.example.lpc.module_main.ui.fragment.navigation

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.lpc.lib_common.http.pojo.Navigation
import com.example.lpc.module_main.R

/**
 * Author: liupengchao
 * Date: 2022/4/14
 * ClassName :NavigationAdapter
 * Desc:
 */
class NavigationAdapter(datas: MutableList<Navigation>) :
    BaseQuickAdapter<Navigation, BaseViewHolder>(R.layout.item_navigation, datas) {

    var selectPosition = 0

    override fun convert(holder: BaseViewHolder, item: Navigation) {
        //设置名称
        holder.setText(R.id.tv_name, item.name)
        //
        if (selectPosition == holder.adapterPosition) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.color_navigation_select_bg))
        } else {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.color_f2f2f2
                )
            )
        }
    }
}