package com.example.lpc.module_main.ui.activity.ui.register

/**
 * Author: liupengchao
 * Date: 2022/3/29
 * ClassName :RegisterFormState
 * Desc:
 */
data class RegisterFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)