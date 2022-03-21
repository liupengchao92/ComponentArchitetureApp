package com.example.lpc.lib_common.http

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :BaseRespond
 * Desc:
 */
class BaseVo<T> {

    val data: T? = null

    var errorMsg: String? = null

    var errorCode: Int = 0

}