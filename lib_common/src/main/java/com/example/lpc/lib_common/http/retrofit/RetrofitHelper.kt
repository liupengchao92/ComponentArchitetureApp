package com.example.lpc.lib_common.http.retrofit

import com.example.lpc.lib_common.BuildConfig
import com.example.lpc.lib_common.application.BaseApplication
import com.example.lpc.lib_common.http.service.APIService
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :RetrofitHelper
 * Desc:
 */
object RetrofitHelper {

    private const val BASE_URL = "https://www.wanandroid.com"

    private const val TIME_OUT: Long = 30 * 1000

    val apiService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }


    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    private fun getOkhttpClient(): OkHttpClient {


        val loggingInterceptor = HttpLoggingInterceptor().apply {

            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }

        val cookieJar = PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(BaseApplication.INSTANCE.applicationContext)
        )

        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .cookieJar(cookieJar)
            /*  .addInterceptor(CookieInterceptor())
              .addInterceptor(CacheInterceptor())*/
            .build();

    }
}