package com.example.lpc.annotation

/**
 * Author: liupengchao
 * Date: 2022/3/27
 * ClassName :TaskInfo
 * Desc:
 */
data class TaskInfo(
    val name: String,
    val background: Boolean,
    val task: ITask
)