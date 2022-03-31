package com.example.lpc.lib_common.http.pojo

import java.io.Serializable

/**
 * Author: liupengchao
 * Date: 2022/3/30
 * ClassName :HotKey
 * Desc:搜索热词
 */
data class HotKey(

    val id: String,

    val link: String,

    val name: String,

    val order: Int,

    val visible: Int
):Serializable
