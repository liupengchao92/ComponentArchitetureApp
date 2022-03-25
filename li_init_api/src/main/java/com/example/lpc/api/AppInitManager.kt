package com.example.lpc.api

import android.app.Application
import android.util.Log

/**
 * Author: liupengchao
 * Date: 2022/3/25
 * ClassName :AppInitManager
 * Desc:
 */
class AppInitManager private constructor(val application: Application, val processName: String) {

    fun start() {

        var register = FinalTaskRegister.register()
        Log.e("AppInitManager", "start: ${register.size}")
        register.forEach {
            ( it as IInitTask).execute(application)
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