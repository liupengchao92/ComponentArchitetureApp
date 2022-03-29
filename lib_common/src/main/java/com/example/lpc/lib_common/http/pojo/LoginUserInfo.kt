package com.example.lpc.lib_common.http.pojo

/**
 * Author: liupengchao
 * Date: 2022/3/29
 * ClassName :LoginUserInfo
 * Desc:用户信息
 */
data class LoginUserInfo(

    val admin: Boolean,

    val chapterTops: MutableList<String>,

    val coinCount: Int,

    val collectIds: MutableList<String>,

    val email: String,

    val icon: String,

    val id: String,

    val nickname: String,

    val password: String,

    val publicName: String,

    val token: String,

    val type: String,

    val username: String

)
