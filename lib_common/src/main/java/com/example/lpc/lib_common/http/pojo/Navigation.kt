package com.example.lpc.lib_common.http.pojo

/**
 * Author: liupengchao
 * Date: 2022/4/14
 * ClassName :Navigation
 * Desc:
 */
data class Navigation(
    val cid: String,
    val name: String,
    val articles: MutableList<Article>
)