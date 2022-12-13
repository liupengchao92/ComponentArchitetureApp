package com.example.module_web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lpc.module_web.R
import com.example.module_web.webview.BaseWebChromeClient
import com.example.module_web.webview.BaseWebView
import com.example.module_web.webview.BaseWebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webView.run {

            loadUrl("https://www.baidu.com")

            setCustomChromeClient(BaseWebChromeClient())

            setCustomWebViewClient(BaseWebViewClient())
        }
    }
}