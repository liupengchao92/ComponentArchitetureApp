package com.example.lpc.module_main.ui.activity.ui.register

import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.http.Results

/**
 * Author: liupengchao
 * Date: 2022/3/28
 * ClassName :RegisterRepository
 * Desc:
 */
class RegisterRepository(private val dataSource: RegisterDataSource) :
    BaseRepositoryRemote<RegisterDataSource>(dataSource) {

    /**
     * 注册
     * @param username String
     * @param password String
     * @param repassword String
     * @return Results<String>
     */
    suspend fun register(username: String, password: String, repassword: String): Results<String> {

        return dataSource.register(username, password, repassword)
    }
}