package com.example.module_web.webview

import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * 创建人:liupengchao
 * 创建日期:2022/12/7 20:08
 * 描述:
 */
class BaseWebViewClient :WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        super.onReceivedSslError(view, handler, error)
    }
}