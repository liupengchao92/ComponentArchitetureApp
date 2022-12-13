package com.example.module_web.webview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.lpc.module_web.R

/**
 * 创建人:liupengchao
 * 创建日期:2022/12/7 19:53
 * 描述:BaseWebView
 */
class BaseWebView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) :
    WebView(context, attr, defStyle) {

    init {

        //设置背景透明
        setBackgroundColor(Color.TRANSPARENT)
        //设置背景颜色
        setBackgroundResource(R.color.white)
        //滑动模式
        overScrollMode = OVER_SCROLL_NEVER
        //默认支持嵌套滑动
        isNestedScrollingEnabled = true
        // WebView 调试模式开关
        setWebContentsDebuggingEnabled(true)
        // 不显示滚动条
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false
        //设置WebSettings
        WebUtils.defaultSetting(this)
    }


    /**
     * 设置WebViewClient
     */
    fun setCustomWebViewClient(webViewClient: BaseWebViewClient?) {
        if (webViewClient == null) {
            super.setWebViewClient(WebViewClient())
        } else {
            super.setWebViewClient(webViewClient)
        }
    }

    /**
     * 设置ebChromeClient
     */
    fun setCustomChromeClient(chromeClient: BaseWebChromeClient?) {
        if (chromeClient == null) {
            super.setWebChromeClient(WebChromeClient())
        } else {
            super.setWebChromeClient(chromeClient)
        }
    }

}