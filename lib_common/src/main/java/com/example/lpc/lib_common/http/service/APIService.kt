package com.example.lpc.lib_common.http.service

import com.example.lpc.lib_common.http.BaseVo
import com.example.lpc.lib_common.http.pojo.*
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
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<BaseVo<LoginUserInfo>>


    /**
     *
     * @param username String
     * @param password String
     * @param repassword String
     * @return Response<BaseVo<String>>
     * @Desc:注册：https://www.wanandroid.com/user/register
     */
    @POST("/user/register")
    suspend fun register(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("repassword") repassword: String
    ): Response<BaseVo<String>>

    /**
     * @return Response<BaseVo<Any>>
     * @Desc:退出登录：https://www.wanandroid.com/user/logout/json
     */
    @GET("/user/logout/json")
    suspend fun logout(): Response<BaseVo<Any>>


    /**
     *
     * @return Response<BaseVo<MutableList<Banner>>>
     * @Desc:首页轮播图：https://www.wanandroid.com/banner/json
     */
    @GET("/banner/json")
    suspend fun getBanner(): Response<BaseVo<MutableList<Banner>>>

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
     * @return Response<BaseVo<MutableList<Article>>>
     * @Desc:首页置顶文章：https://www.wanandroid.com/article/top/json
     */
    @GET("/article/top/json")
    suspend fun getTopArticle(): Response<BaseVo<MutableList<Article>>>

    /**
     *
     * @return Response<BaseVo<HotKey>>
     * @Desc :搜索热词：https://www.wanandroid.com//hotkey/json
     */
    @GET("/hotkey/json")
    suspend fun getSearchHotKey(): Response<BaseVo<MutableList<HotKey>>>

    /**
     * @return Response<BaseVo<PageVo<Article>>>
     * @Desc:文章搜索：https://www.wanandroid.com/article/query/0/json
     */
    @POST("/article/query/{page}/json")
    suspend fun getSearchArticle(
        @Path("page") page: Int,
        @Query("k") keyword: String
    ): Response<BaseVo<PageVo<Article>>>


    /**
     * @return Results<BaseVo<PersonalInfo>>
     * @Desc:个人信息：https://wanandroid.com//user/lg/userinfo/json
     */
    @GET("/user/lg/userinfo/json")
    suspend fun getUserInfo(): Response<BaseVo<PersonalInfo>>

    /**
     * @param id Int
     * @return Response<BaseVo<Any>>
     * @Desc:收藏：https://www.wanandroid.com/lg/collect/1165/json
     */
    @POST("/lg/collect/{id}/json")
    suspend fun collectInternalArticle(@Path("id") id: String): Response<BaseVo<Any>>


    /**
     * 取消收藏：https://www.wanandroid.com/lg/uncollect_originId/2333/json
     * @param id Int
     * @return Response<BaseVo<Any>>
     */
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun cancelCollectInternalArticle(@Path("id") id: String): Response<BaseVo<Any>>

    /**
     * 导航：https://www.wanandroid.com/navi/json
     * @return Response<BaseVo<Any>>
     */
    @GET("/navi/json")
    suspend fun getNavigationData(): Response<BaseVo<MutableList<Navigation>>>

    /**
     *  https://wanandroid.com/wenda/list/1/json
     * @param page Int
     * @return Response<BaseVo<PageVo<Article>>>
     */
    @GET("/wenda/list/{page}/json")
    suspend fun getQuestionData(@Path("page") page: Int): Response<BaseVo<PageVo<Article>>>

    /**
     *https://wanandroid.com/user_article/list/页码/json
     * @param page Int
     * @return Response<BaseVo<PageVo<Article>>>
     */
    @GET("/user_article/list/{page}/json")
    suspend fun getSquareList(@Path("page") page: Int): Response<BaseVo<PageVo<Article>>>
}