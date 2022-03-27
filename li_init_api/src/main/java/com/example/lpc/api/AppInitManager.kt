package com.example.lpc.api

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.concurrent.Executors

/**
 * Author: liupengchao
 * Date: 2022/3/25
 * ClassName :AppInitManager
 * Desc:
 */
class AppInitManager private constructor(private val app: Application, val processName: String) {
    //
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    private val threadPool = Executors.newFixedThreadPool(3)

    /**
     * 开始任务
     */
    fun start() {

        var register = FinalTaskRegister.register()
        Log.e("AppInitManager", "start: ${register.size}")
        register.forEach {

            if (it.background) {

                threadPool.execute {
                    execute(it.task as IInitTask)
                }

            } else {

                if (Looper.myLooper() == Looper.getMainLooper()) {
                    execute(it.task as IInitTask)
                } else {
                    handler.post(Runnable {
                        execute(it.task as IInitTask)
                    })
                }
            }
        }
    }

    private fun execute(task: IInitTask) {

        runCatching {
            task.execute(app)
        }.onFailure {

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