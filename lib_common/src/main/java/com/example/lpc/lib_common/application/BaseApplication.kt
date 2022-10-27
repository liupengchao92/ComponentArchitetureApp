package com.example.lpc.lib_common.application

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lpc.api.AppInitManager
import com.example.lpc.lib_common.utils.DarkModeUtils

/**
 * Author: liupengchao
 * Date: 2022/3/15
 * ClassName :BaseApplication
 * Desc:基础
 */
open class BaseApplication() : Application() {


    companion object {

        lateinit var INSTANCE: Application

    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        //初始化路由
        ARouter.init(this)
        ARouter.openLog()
        ARouter.openDebug()
        //初始化任务
        AppInitManager.start(this)
        //暗黑模式
        DarkModeUtils.init()

    }

}