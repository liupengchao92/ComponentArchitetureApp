package com.example.lpc.module_profile.ui.collect

import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/6/14
 * ClassName :CollectListRepository
 * Desc:
 */
class CollectListRepository(private val dataSource: CollectListDatasource) :
    BaseRepositoryRemote<CollectListDatasource>(dataSource) {

    suspend fun getCollectList(page: Int): Results<PageVo<Article>> {
        return dataSource.getCollectList(page)
    }

}


class CollectListDatasource : IRemoteDataSource {

    suspend fun getCollectList(page: Int): Results<PageVo<Article>> {

        return processApiResponse {
            RetrofitHelper.apiService.getCollectList(page)
        }
    }
}