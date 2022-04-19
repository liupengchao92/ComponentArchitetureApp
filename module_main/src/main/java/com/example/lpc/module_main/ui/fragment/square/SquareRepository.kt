package com.example.lpc.module_main.ui.fragment.square

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper
import com.example.lpc.module_main.ui.fragment.square.paging.SquarePagingSource
import kotlinx.coroutines.flow.Flow

/**
 * Author: liupengchao
 * Date: 2022/4/19
 * ClassName :SquareRepository
 * Desc:
 */
class SquareRepository(val datasource: SquareDatasource) :
    BaseRepositoryRemote<SquareDatasource>(datasource) {

    suspend fun getSquareList(page: Int): Results<PageVo<Article>> {

        return datasource.getSquareList(page)
    }

    fun getSquareListByPaging(): Flow<PagingData<Article>> {

        return Pager(config = PagingConfig(pageSize = 20)) {
            SquarePagingSource(datasource)
        }.flow
    }
}


class SquareDatasource : IRemoteDataSource {

    suspend fun getSquareList(page: Int): Results<PageVo<Article>> {

        return processApiResponse { RetrofitHelper.apiService.getSquareList(page) }
    }
}