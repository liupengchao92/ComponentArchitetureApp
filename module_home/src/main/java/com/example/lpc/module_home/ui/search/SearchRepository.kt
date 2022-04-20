package com.example.lpc.module_home.ui.search

import com.example.lpc.lib_common.base.repository.BaseRepositoryBoth
import com.example.lpc.lib_common.base.repository.ILocalDataSource
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.database.DatabaseManager
import com.example.lpc.lib_common.database.dao.KeywordDao
import com.example.lpc.lib_common.database.entity.KeyWord
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

    suspend fun getAllKeyWord(): MutableList<KeyWord> {
        return localDataSource.getAll()
    }

    suspend fun queryByKeyword(keyword: String):MutableList<KeyWord>{
        return localDataSource.queryByKeyword(keyword)
    }

    suspend fun insertKeyWord(keyWord: String) {
        localDataSource.insertAll(keyWord)
    }

    suspend fun delete(keyWord: KeyWord) {
        localDataSource.delete(keyWord)
    }
}


class SearchRemoteDataSource : IRemoteDataSource {

    suspend fun getSearchArticle(page: Int, keyword: String): Results<PageVo<Article>> {

        return processApiResponse { RetrofitHelper.apiService.getSearchArticle(page, keyword) }
    }

}

class SearchLocalDataSource(private val dao:KeywordDao) : ILocalDataSource {

    suspend fun getAll(): MutableList<KeyWord> {

        return dao.getAllKeyWord()
    }

    suspend fun queryByKeyword(keyword: String): MutableList<KeyWord> {
        return dao.queryByKeyWord(keyword)
    }

    suspend fun insertAll(keyword: String) {
        dao.insertAll(KeyWord(keyWord = keyword))
    }

    suspend fun delete(keyWord: KeyWord) {
        dao.delete(keyWord)
    }

}