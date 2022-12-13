package com.example.module_web.webview

import android.content.Context
import android.webkit.WebSettings
import android.webkit.WebView
import java.io.File

/**
 * 创建人:liupengchao
 * 创建日期:2022/12/7 20:12
 * 描述:
 */
object WebUtils {

    /**
     * WebSettings 默认设置
     */
    fun defaultSetting(webView: WebView) {

        webView.settings.run {
            // 是否支持缩放，默认为true
            setSupportZoom(false)
            // 是否使用内置的缩放控件
            builtInZoomControls = false
            // 是否显示原生的缩放控件
            displayZoomControls = false
            // 设置文本缩放 默认 100
            textZoom = 100
            // 是否保存密码
            savePassword = false
            // 是否可以访问文件
            allowFileAccess = true
            // 是否支持通过js打开新窗口
            javaScriptCanOpenWindowsAutomatically = true
            // 是否支持自动加载图片
            loadsImagesAutomatically = true
            blockNetworkImage = false
            // 设置编码格式
            defaultTextEncodingName = "utf-8"
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
            // 是否启用 DOM storage API
            domStorageEnabled = true
            // 是否启用 database storage API 功能
            databaseEnabled = true
            // 配置当安全源试图从不安全源加载资源时WebView的行为
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            // 设置缓存模式
            cacheMode = WebSettings.LOAD_DEFAULT
            // 开启 Application Caches 功能
            setAppCacheEnabled(true)

            // 设置 Application Caches 缓存目录
            val cachePath = getWebViewCachePath(webView.context)
            val cacheDir = File(cachePath)
            // 设置缓存目录
            if (!cacheDir.exists() && !cacheDir.isDirectory) {
                cacheDir.mkdirs()
            }
            setAppCachePath(cachePath)
        }

    }

    private fun getWebViewCachePath(context: Context): String? {
        return "";
    }
}