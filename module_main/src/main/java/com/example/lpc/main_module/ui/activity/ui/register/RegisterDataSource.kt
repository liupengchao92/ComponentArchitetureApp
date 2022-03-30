package com.example.lpc.main_module.ui.activity.ui.register

import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/3/28
 * ClassName :RegisterDataSource
 * Desc:
 */
class RegisterDataSource : IRemoteDataSource {

    /**
     * 注册
     * @param username String
     * @param password String
     * @param repassword String
     * @return Results<String>
     */
    suspend fun register(username: String, password: String, repassword: String): Results<String> {

        return processApiResponse {
            RetrofitHelper.apiService.register(
                username,
                password,
                repassword
            )
        }
    }

}