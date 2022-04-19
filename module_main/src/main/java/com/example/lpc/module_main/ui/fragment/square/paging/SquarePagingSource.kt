package com.example.lpc.module_main.ui.fragment.square.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.module_main.ui.fragment.square.SquareDatasource

/**
 * Author: liupengchao
 * Date: 2022/4/19
 * ClassName :SquarePagingSource
 * Desc:
 */
class SquarePagingSource(private val datasource: SquareDatasource) : PagingSource<Int, Article>() {


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {

       /* return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }*/

        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        try {
            //如果未定义，则从第0页开始刷新。
            val nextPage = params.key ?: 0
            //从服务器请求的数据
            val results = datasource.getSquareList(nextPage)
            //请求成功
            return if (results is Results.Success) {
                LoadResult.Page(
                    data = results.data.datas!!,
                    prevKey = null,
                    nextKey = results.data.curPage
                )
            } else {
                LoadResult.Error((results as Results.Failure).throwable)
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}

