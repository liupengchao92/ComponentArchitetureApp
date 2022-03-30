package com.example.lpc.module_main.ui.activity.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Author: liupengchao
 * Date: 2022/3/28
 * ClassName :RegisterViewModelFactory
 * Desc:
 */
class RegisterViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return RegisterViewModel(
            repository = RegisterRepository(
                dataSource = RegisterDataSource()
            )
        ) as T
    }
}