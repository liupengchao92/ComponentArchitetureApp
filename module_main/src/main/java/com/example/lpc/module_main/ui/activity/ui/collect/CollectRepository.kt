package com.example.lpc.module_main.ui.activity.ui.collect

import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/4/22
 * ClassName :CollectRepository
 * Desc:收藏
 */
class CollectRepository(private val dataSource: CollectDataSource) :
    BaseRepositoryRemote<CollectDataSource>(dataSource) {

    suspend fun collectArticle(id: String): Results<Any> {
        return dataSource.collectArticle(id)
    }

    suspend fun cancelCollectArticle(id: String): Results<Any> {
        return dataSource.cancelCollectArticle(id)
    }
}


class CollectDataSource : IRemoteDataSource {

    suspend fun collectArticle(id: String): Results<Any> {

        return processApiResponse { RetrofitHelper.apiService.collectInternalArticle(id) }
    }

    suspend fun cancelCollectArticle(id: String): Results<Any> {
        return processApiResponse { RetrofitHelper.apiService.cancelCollectInternalArticle(id) }
    }
}