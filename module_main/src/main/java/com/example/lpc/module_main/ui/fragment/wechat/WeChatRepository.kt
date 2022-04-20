package com.example.lpc.module_main.ui.fragment.wechat

import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.pojo.WxChapter
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/4/20
 * ClassName :WeChatRepository
 * Desc:
 */
class WeChatRepository(private val datasource: WeChatRemoteDatasource) :
    BaseRepositoryRemote<WeChatRemoteDatasource>(datasource) {

    suspend fun getWxChapterList(): Results<MutableList<WxChapter>> {

        return datasource.getWxChapterList()
    }

    suspend fun getWxArticle(page: Int,id: String):Results<PageVo<Article>>{

        return datasource.getWxArticle(page, id)
    }
}


class WeChatRemoteDatasource : IRemoteDataSource {

    suspend fun getWxChapterList(): Results<MutableList<WxChapter>> {

        return processApiResponse { RetrofitHelper.apiService.getWxChapters() }
    }

    suspend fun getWxArticle(page: Int, id: String): Results<PageVo<Article>> {

        return processApiResponse { RetrofitHelper.apiService.getWxArticle(page, id) }
    }

    suspend fun searchArticle(page: Int, id: String, keyword: String): Results<PageVo<Article>> {

        return processApiResponse { RetrofitHelper.apiService.searchWxArticle(page, id, keyword) }
    }
}