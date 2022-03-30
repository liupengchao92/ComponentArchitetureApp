package com.example.lpc.module_main.ui.activity

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lpc.lib_common.base.activity.BaseActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.module_main.R


@Route(path = ARouterConstant.Main.INDEX_PATH)
class MainActivity : BaseActivity() {

    override var layoutResId: Int = R.layout.activity_main


    override fun supportNavigateUpTo(upIntent: Intent) {
        super.supportNavigateUpTo(upIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}