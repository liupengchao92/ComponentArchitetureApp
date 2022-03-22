package com.example.lpc.lib_common.http

/**
 * Author: liupengchao
 * Date: 2022/3/22
 * ClassName :Error
 * Desc:
 */
sealed class Errors : Throwable() {

    data class NetWorException(val errorCode: Int = -1, val errorMsg: String? = "") : Errors()

    data class EmptyResultsError(val errorMsg: String? = "result isn null") : Errors()

}