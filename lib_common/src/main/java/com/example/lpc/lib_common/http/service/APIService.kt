package com.example.lpc.lib_common.http.service

import com.example.lpc.lib_common.http.BaseVo
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.Banner
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


    //首页文章：https://www.wanandroid.com/article/list/1/json
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): Response<BaseVo<PageVo<Article>>>

    //首页轮播图：https://www.wanandroid.com/banner/json
    @GET("/banner/json")
    suspend fun getBanner(): Response<BaseVo<MutableList<Banner>>>


}