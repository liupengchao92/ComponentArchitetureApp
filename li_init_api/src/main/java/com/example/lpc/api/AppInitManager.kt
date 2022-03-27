package com.example.lpc.api

import android.app.Application
import android.util.Log

/**
 * Author: liupengchao
 * Date: 2022/3/25
 * ClassName :AppInitManager
 * Desc:
 */
class AppInitManager private constructor(private val app: Application, val processName: String) {

    /**
     * 开始任务
     */
    fun start() {

        var register = FinalTaskRegister.register()
        Log.e("AppInitManager", "start: ${register.size}")
        register.forEach {
            (it.task as IInitTask).execute(app)
        }

    }


    companion object {

        fun start(application: Application) {
            start(application, ProcessUtils.getProcessName(application))
        }

        fun start(application: Application, processName: String) {
            AppInitManager(application, processName).start()
        }
    }
}