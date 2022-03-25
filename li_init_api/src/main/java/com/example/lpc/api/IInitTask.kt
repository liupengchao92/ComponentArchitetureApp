package com.example.lpc.api

import android.app.Application
import com.example.lpc.annotation.ITask

/**
 * Author: liupengchao
 * Date: 2022/3/25
 * ClassName :IInitTask
 * Desc:
 */
interface IInitTask : ITask {

    fun execute(application: Application)
}