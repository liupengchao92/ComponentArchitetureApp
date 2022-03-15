package com.example.lpc.componentachitecture.application

import android.util.Log
import com.example.lpc.lib_common.application.BaseApplication

/**
 * Author: liupengchao
 * Date: 2022/3/15
 * ClassName :MyApplication
 * Desc:
 */
class MyApplication() : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        Log.d("MyApplication", "onCreate: ")
    }
}