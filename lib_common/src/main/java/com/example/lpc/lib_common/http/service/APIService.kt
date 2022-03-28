package com.example.lpc.lib_common.http.service

import com.example.lpc.lib_common.http.BaseVo
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.Banner
import com.example.lpc.lib_common.http.pojo.PageVo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :APIService
 * Desc:API
 */
interface APIService {


    /**
     *
     * @param username String
     * @param password String
     * @return Response<BaseVo<String>>
     * @Desc:登录：https://www.wanandroid.com/user/login
     */
    @POST("/user/login")
    suspend fun login(@Query("username") username: String, @Query("password") password: String): Response<BaseVo<String>>

    /**
     *
     * @param page Int
     * @return Response<BaseVo<PageVo<Article>>>
     * @Desc:首页文章：https://www.wanandroid.com/article/list/1/json
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): Response<BaseVo<PageVo<Article>>>

    /**
     *
     * @return Response<BaseVo<MutableList<Banner>>>
     * @Desc:首页轮播图：https://www.wanandroid.com/banner/json
     */
    @GET("/banner/json")
    suspend fun getBanner(): Response<BaseVo<MutableList<Banner>>>


}