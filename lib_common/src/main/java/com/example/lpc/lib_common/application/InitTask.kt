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

@InitTask(name = "MainTask")
class MainTask : IInitTask {


    override fun execute(application: Application) {

        LogUtils.e("执行任务=======>>MainTask")
    }

}