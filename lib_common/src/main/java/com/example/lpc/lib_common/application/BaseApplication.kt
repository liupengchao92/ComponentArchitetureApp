package com.example.lpc.lib_common.application

import android.app.Application
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Author: liupengchao
 * Date: 2022/3/15
 * ClassName :BaseApplication
 * Desc:基础
 */
open class BaseApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化路由
        ARouter.init(this)
        ARouter.openLog()
        ARouter.openDebug()

        Log.e("BaseApplication", "onCreate: ", )
    }

}