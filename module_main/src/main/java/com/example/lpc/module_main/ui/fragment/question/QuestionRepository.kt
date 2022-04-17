package com.example.lpc.module_main.ui.fragment.question

import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/4/17
 * ClassName :QuestionRepository
 * Desc:
 */
class QuestionRepository(private val datasource: QuestionRemoteDatasource) :
    BaseRepositoryRemote<QuestionRemoteDatasource>(datasource) {

    suspend fun getQuestion(page: Int): Results<PageVo<Article>> {
        return datasource.getQuestion(page)
    }
}


class QuestionRemoteDatasource : IRemoteDataSource {

    suspend fun getQuestion(page: Int): Results<PageVo<Article>> {

        return processApiResponse { RetrofitHelper.apiService.getQuestionData(page) }
    }

}