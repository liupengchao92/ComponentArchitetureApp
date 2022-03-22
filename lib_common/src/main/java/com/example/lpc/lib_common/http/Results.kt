package com.example.lpc.lib_common.http

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :Results
 * Desc:密封类
 */
sealed class Results<out T> {

    companion object {

        fun <T> success(data: T): Results<T> = Success(data);

        fun <T> failure(error: Throwable): Results<T> = Failure(error)
    }

    data class Success<out T>(val data: T) : Results<T>()

    data class Failure(val throwable: Throwable) : Results<Nothing>()
}