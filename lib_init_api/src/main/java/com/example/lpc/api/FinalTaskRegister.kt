package com.example.lpc.api

import com.example.lpc.annotation.ModuleTaskRegister
import com.example.lpc.annotation.TaskInfo
import java.lang.reflect.InvocationTargetException

/**
 * Author: liupengchao
 * Date: 2022/3/25
 * ClassName :FinalTaskRegister
 * Desc:
 */
object FinalTaskRegister {

    val taskList: MutableList<TaskInfo> = mutableListOf()

    init {
        init()
    }

    private fun init() {}

    fun register(register: ModuleTaskRegister) {
        register.register(taskList)
    }
}