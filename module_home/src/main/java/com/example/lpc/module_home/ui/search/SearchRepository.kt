package com.example.lpc.module_home.ui.search

import com.example.lpc.lib_common.base.repository.BaseRepositoryBoth
import com.example.lpc.lib_common.base.repository.ILocalDataSource
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/3/31
 * ClassName :SearchRepository
 * Desc:
 */
class SearchRepository(
    remoteDataSource: SearchRemoteDataSource,
    localDataSource: SearchLocalDataSource
) : BaseRepositoryBoth<SearchRemoteDataSource, SearchLocalDataSource>(
    remoteDataSource,
    localDataSource
) {


    suspend fun getSearchArticle(page: Int, keyword: String): Results<PageVo<Article>> {
        return remoteDataSource.getSearchArticle(page, keyword)
    }


}


class SearchRemoteDataSource : IRemoteDataSource {

    suspend fun getSearchArticle(page: Int, keyword: String): Results<PageVo<Article>> {

        return processApiResponse { RetrofitHelper.apiService.getSearchArticle(page, keyword) }
    }

}

class SearchLocalDataSource : ILocalDataSource {

}