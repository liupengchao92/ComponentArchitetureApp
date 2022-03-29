package com.example.lpc.main_module.ui.activity.ui.login

import com.example.lpc.lib_common.http.pojo.LoginUserInfo

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(

    val success: LoginUserInfo? = null,

    val error: String? = null
)