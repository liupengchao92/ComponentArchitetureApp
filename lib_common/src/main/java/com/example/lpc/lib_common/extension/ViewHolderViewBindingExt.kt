package com.example.lpc.lib_common.extension

import android.view.View
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Author: liupengchao
 * Date: 2022/4/15
 * ClassName :ViewHolderViewBindingExt
 * Desc:ViewHolder 的封装
 */


fun <VB : ViewBinding> BaseViewHolder.getViewBinding(bind: (View) -> VB): VB {
/*
    var vb = itemView.getTag(Int.MAX_VALUE) as? VB ? : bing()

    if (vb == null) {

        vb = bind(itemView).also {

            itemView.setTag(Int.MAX_VALUE, it)
        }
    }
    return vb
*/
 return   itemView.getTag(Int.MIN_VALUE) as? VB ?: bind(itemView).also { itemView.setTag(Int.MIN_VALUE, it) }

}