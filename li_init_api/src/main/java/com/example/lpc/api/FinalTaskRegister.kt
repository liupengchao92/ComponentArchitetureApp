package com.example.lpc.api

import com.example.lpc.annotation.ITask
import com.example.lpc.annotation.ModuleTaskRegister
import java.lang.reflect.InvocationTargetException

/**
 * Author: liupengchao
 * Date: 2022/3/25
 * ClassName :FinalTaskRegister
 * Desc:
 */
object FinalTaskRegister {

    fun register(): MutableList<ITask> {
        var taskList = mutableListOf<ITask>()
        try {
            val taskRegister = Class.forName("com.example.lpc.register.TaskRegister")
            var newInstance: ModuleTaskRegister = taskRegister.newInstance() as ModuleTaskRegister

            newInstance.register(taskList)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }

        return taskList;
    }
}