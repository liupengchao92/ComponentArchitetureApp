package com.example.lpc.lib_common.base.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo

/**
 * Author: liupengchao
 * Date: 2022/4/22
 * ClassName :CustomPagingSource
 * Desc:文章
 */
class ArticlePagingSource(private val request: (Int) -> Result<PageVo<Article>>) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        return try {
            //要请求的页数
            val currPage: Int = params.key ?: 0
            //获取请求的数据
            val result = request.invoke(currPage)

            return if (result is Results.Success<*>) {
                val pageVo = result.data as PageVo<Article>

                LoadResult.Page(
                    data = pageVo.datas!!,
                    prevKey = null,
                    nextKey = pageVo.curPage
                )
            } else {
                LoadResult.Error((result as Results.Failure).throwable)
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }
}