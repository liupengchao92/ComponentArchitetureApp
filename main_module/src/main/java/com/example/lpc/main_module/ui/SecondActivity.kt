package com.example.lpc.main_module.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.main_module.R

@Route(path = ARouterConstant.Main.SECOND_PATH)
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}