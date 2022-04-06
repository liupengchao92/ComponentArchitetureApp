package com.example.lpc.module_main.ui.activity.ui.web

import android.os.Bundle
import com.example.lpc.lib_common.base.activity.BaseActivity
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.module_main.R
import kotlinx.android.synthetic.main.activity_common_web.*

class CommonWebActivity : BaseActivity() {

    override var layoutResId: Int = R.layout.activity_common_web

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var article = intent.extras?.get(ParamsKeyConstant.ARTICLE) as Article


        toolBar.run {
            title = article.title
            setSupportActionBar(this)

        }


        webView.loadUrl(article.link)
    }
}