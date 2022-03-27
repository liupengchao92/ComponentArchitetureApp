package com.example.lpc.lib_common.application

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.example.lpc.annotation.InitTask
import com.example.lpc.api.IInitTask


/**
 * Author: liupengchao
 * Date: 2022/3/24
 * ClassName :InitTask
 * Desc:
 */

@InitTask(name = "MainTask",background = true)
class MainTask : IInitTask {


    override fun execute(application: Application) {

        LogUtils.e("MainTask=======>>${Thread.currentThread().name}")
    }

}