package com.example.lpc.module_main.ui.fragment.navigation

import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Navigation
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/4/14
 * ClassName :NavigationRepository
 * Desc:
 */
class NavigationRepository(val dataSource: NavigationDataSource) :
    BaseRepositoryRemote<NavigationDataSource>(dataSource) {


    suspend fun getNavigation(): Results<MutableList<Navigation>> {

        return dataSource.getNavigation()
    }

}


class NavigationDataSource : IRemoteDataSource {

    suspend fun getNavigation(): Results<MutableList<Navigation>> {
        return processApiResponse { RetrofitHelper.apiService.getNavigationData() }
    }

}