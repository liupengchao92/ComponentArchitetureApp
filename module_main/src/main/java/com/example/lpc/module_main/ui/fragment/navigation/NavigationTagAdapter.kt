package com.example.lpc.module_main.ui.fragment.navigation

import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import com.blankj.utilcode.util.SizeUtils
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.module_main.R
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter

/**
 * Author: liupengchao
 * Date: 2022/4/14
 * ClassName :NavigationTagAdapter
 * Desc:
 */
class NavigationTagAdapter(tags: MutableList<Article>) : TagAdapter<Article>(tags) {

    override fun getView(parent: FlowLayout?, position: Int, item: Article?): View {

        return TextView(parent?.context).apply {

            text = item?.title

            textSize = 15f

            gravity = Gravity.CENTER

            setPadding(30, 8, 30, 8)

            setTextColor(ContextCompat.getColor(this.context, R.color.color_navigation_tag_text))

            setBackgroundResource(R.drawable.shape_navigation_tag_bg)
        }
    }
}