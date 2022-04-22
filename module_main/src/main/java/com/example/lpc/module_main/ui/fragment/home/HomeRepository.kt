package com.example.lpc.module_main.ui.repository

import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.Banner
import com.example.lpc.lib_common.http.pojo.HotKey
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/3/22
 * ClassName :HomeRepository
 * Desc:
 */
class HomeRepository(var dataSource: HomeRemoteDataSource) :

    BaseRepositoryRemote<HomeRemoteDataSource>(dataSource) {

    suspend fun getArticle(page: Int): Results<PageVo<Article>> {

        return dataSource.getArticle(page)
    }

    suspend fun getBanner(): Results<MutableList<Banner>> {
        return dataSource.getBanner()
    }

    suspend fun getTop(): Results<MutableList<Article>> {
        return dataSource.getTop()
    }

    suspend fun getSearchHotKey(): Results<MutableList<HotKey>> {

        return dataSource.getSearchHotKey()
    }
}

class HomeRemoteDataSource : IRemoteDataSource {

    suspend fun getArticle(page: Int): Results<PageVo<Article>> {

        return processApiResponse { RetrofitHelper.apiService.getHomeArticle(page) }
    }

    suspend fun getBanner(): Results<MutableList<Banner>> {
        return processApiResponse { RetrofitHelper.apiService.getBanner() }
    }

    suspend fun getTop(): Results<MutableList<Article>> {

        return processApiResponse { RetrofitHelper.apiService.getTopArticle() }
    }

    suspend fun getSearchHotKey(): Results<MutableList<HotKey>> {

        return processApiResponse { RetrofitHelper.apiService.getSearchHotKey() }
    }
}

