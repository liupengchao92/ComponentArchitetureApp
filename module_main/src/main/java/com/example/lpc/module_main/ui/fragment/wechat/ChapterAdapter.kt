package com.example.lpc.module_main.ui.fragment.wechat

import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.lpc.lib_common.http.pojo.WxChapter
import com.example.lpc.module_main.R
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Author: liupengchao
 * Date: 2022/4/20
 * ClassName :ChapterAdapter
 * Desc:
 */
class ChapterAdapter : BaseQuickAdapter<WxChapter, BaseViewHolder>(R.layout.item_chapter) {

    var selectPosition = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun convert(holder: BaseViewHolder, item: WxChapter) {

        //名称
        holder.setText(R.id.tv_name, item.name)

        //选中位置
        if (selectPosition == holder.bindingAdapterPosition) {

            ColorUtils.getColor(R.color.colorPrimary).let { color ->

                holder.getView<CircleImageView>(R.id.iv_head).borderColor = color

                holder.setTextColor(R.id.tv_name,color)
            }

        }else{

            holder.getView<CircleImageView>(R.id.iv_head).borderColor =  ColorUtils.getColor(R.color.white)

            holder.setTextColor(R.id.tv_name, ColorUtils.getColor(R.color.gray))
        }
    }
}