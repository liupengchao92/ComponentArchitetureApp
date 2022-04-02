package com.example.lpc.module_main.ui.fragment.profile

import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.PersonalInfo
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/4/1
 * ClassName :ProfileRepository
 * Desc:
 */
class ProfileRepository(private val dataSource: ProfileDataSource) :
    BaseRepositoryRemote<ProfileDataSource>(dataSource) {

    suspend fun getUserInfo(): Results<PersonalInfo> {
        return dataSource.getUserInfo()
    }
}

class ProfileDataSource : IRemoteDataSource {

    suspend fun getUserInfo(): Results<PersonalInfo> {
        return processApiResponse { RetrofitHelper.apiService.getUserInfo() }
    }
}