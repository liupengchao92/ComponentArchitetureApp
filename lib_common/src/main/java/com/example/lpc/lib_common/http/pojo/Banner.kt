package com.example.lpc.lib_common.http.pojo

/**
 * Author: liupengchao
 * Date: 2022/3/22
 * ClassName :Banner
 * Desc:
 */
data class Banner(
    var desc: String,
    var id: String,
    var imagePath: String,
    var isVisible: Int,
    var order: Int,
    var title: String,
    var type: String,
    var url: String,
)