package com.example.lpc.module_main.ui.activity.ui.login

import com.example.lpc.lib_common.base.repository.ILocalDataSource
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.LoginUserInfo
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/3/28
 * ClassName :LoginDataSource
 * Desc:登录
 */

class LoginRemoteDataSource : IRemoteDataSource {
    /**
     * 登录
     * @param username String
     * @param password String
     * @return Results<String>
     */
    suspend fun login(username: String, password: String): Results<LoginUserInfo> {

        return try {

            processApiResponse { RetrofitHelper.apiService.login(username, password) }

        } catch (e: Throwable) {
            Results.failure(e)
        }
    }

    /**
     * 退出登录
     */
    suspend fun logout(): Results<Any> {
        return processApiResponse { RetrofitHelper.apiService.logout() }
    }
}

class LoginLocalDataSource : ILocalDataSource {

}