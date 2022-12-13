package com.example.module_web.webview

import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView

/**
 * 创建人:liupengchao
 * 创建日期:2022/12/7 20:04
 * 描述:
 */
class BaseWebChromeClient : WebChromeClient() {

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
    }

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsAlert(view, url, message, result)
    }

    override fun onJsConfirm(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsConfirm(view, url, message, result)
    }
}