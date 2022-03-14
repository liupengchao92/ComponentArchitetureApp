package com.example.lpc.componentachitecture

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lpc.lib_common.eventbus.LiveEventBus
import com.example.lpc.library.TestUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val string = TestUtils.getString();

        LiveEventBus.with<String>("ceshi").observe(this, Observer {

            text.text = it;
        })

        send.setOnClickListener {
            LiveEventBus.with<String>("ceshi").value = "发送的消息"
        }
        send2.setOnClickListener {
            LiveEventBus.with<String>("ceshi").value = "发送的消息2"
        }
    }
}