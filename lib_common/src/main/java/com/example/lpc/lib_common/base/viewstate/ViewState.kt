package com.example.lpc.lib_common.base.viewstate

/**
 * Author: liupengchao
 * Date: 2022/4/12
 * ClassName :UiState
 * Desc:
 */
data class ViewState(

    val loading: Boolean = false,

    var throwable: Throwable? = null
)