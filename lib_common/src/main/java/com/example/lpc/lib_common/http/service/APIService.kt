package com.example.lpc.lib_common.http.service

import com.example.lpc.lib_common.http.BaseVo
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :APIService
 * Desc:
 */
interface APIService {


    //https://www.wanandroid.com/article/list/1/json
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): Response<BaseVo<PageVo<Article>>>


}