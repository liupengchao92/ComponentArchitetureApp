package com.example.lpc.annotation

/**
 * Author: liupengchao
 * Date: 2022/3/24
 * ClassName :InitTask
 * Desc:注解任务
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class InitTask(
    /**
     * 任务名称
     */
    val name: String = "",

    /**
     *是否是子线程
     */
    val background: Boolean = false
)
