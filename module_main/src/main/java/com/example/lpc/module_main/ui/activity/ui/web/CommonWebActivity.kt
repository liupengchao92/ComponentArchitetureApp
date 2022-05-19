package com.example.lpc.module_main.ui.activity.ui.web

import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.base.activity.BaseActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.module_main.R
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_common_web.*

@Route(path = ARouterConstant.Common.WEB)
class CommonWebActivity : BaseActivity() {

    override var layoutResId: Int = R.layout.activity_common_web

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var article = intent.extras?.get(ParamsKeyConstant.ARTICLE) as Article


        toolBar.run {
            title = article.title
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            setNavigationIcon(R.drawable.ic_back_white)
        }

        webView.run {
            //加载URL
            loadUrl(article.link)

            webViewClient = CustomWebViewClient()

            webChromeClient = CustomWebChromeClient()
        }

        webView.settings.run {

            javaScriptEnabled = true  //支持js

            pluginsEnabled = true   //支持插件

            useWideViewPort = false  //将图片调整到适合webview的大小

            setSupportZoom(true);  //支持缩放

            //setLayoutAlgorithm(IX5WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局

            supportMultipleWindows()  //多窗口

            cacheMode = (WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存

            allowFileAccess = true  //设置可以访问文件

            setNeedInitialFocus(true) //当webview调用requestFocus时为webview设置节点

            builtInZoomControls = true //设置支持缩放

            javaScriptCanOpenWindowsAutomatically = true//支持通过JS打开新窗口

            loadWithOverviewMode = true // 缩放至屏幕的大小

            loadsImagesAutomatically = true   //支持自动加载图片

            domStorageEnabled = true

        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event);
    }

    inner class CustomWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(webView: WebView?, url: String?): Boolean {
            //重写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
            webView?.loadUrl(url)

            return true
        }

        override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
            super.onPageStarted(p0, p1, p2)
            //设定加载开始的操作
            progress.visibility = View.VISIBLE
        }

        override fun onPageFinished(p0: WebView?, p1: String?) {
            super.onPageFinished(p0, p1)
            //设定加载结束的操作
            progress.visibility = View.GONE

        }

        override fun onLoadResource(p0: WebView?, p1: String?) {
            super.onLoadResource(p0, p1)
            //设定加载资源的操作
        }

        override fun onReceivedError(p0: WebView?, p1: WebResourceRequest?, p2: WebResourceError?) {
            super.onReceivedError(p0, p1, p2)

            LogUtils.e("onReceivedError====>${p2?.errorCode}")
        }

        override fun onReceivedSslError(
            webView: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            //处理https请求
            //表示等待证书响应
            handler?.proceed()

            //表示挂起连接，为默认方式
            //handler.cancel();

            //可做其他处理
            //handler.handleMessage(null);
        }
    }

    inner class CustomWebChromeClient : WebChromeClient() {

        /**
         * 获得网页的加载进度并显示
         * @param p0 WebView
         * @param p1 Int
         */
        override fun onProgressChanged(p0: WebView?, p1: Int) {
            super.onProgressChanged(p0, p1)
            progress.progress = p1
        }

        /**
         * 获取Web页中的标题
         * @param p0 WebView
         * @param p1 String
         */
        override fun onReceivedTitle(p0: WebView?, p1: String?) {
            super.onReceivedTitle(p0, p1)
        }
    }
}