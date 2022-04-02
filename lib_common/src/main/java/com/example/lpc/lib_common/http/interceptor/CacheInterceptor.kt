package com.example.lpc.lib_common.http.interceptor

import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.constant.MMKVConstant
import com.example.lpc.lib_common.utils.MMKVUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author: liupengchao
 * Date: 2022/4/2
 * ClassName :CacheInterceptor
 * Desc:缓存
 */
class CacheInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        return chain.proceed(chain.request()).also {

            val cookieSet = mutableSetOf<String>()

            it.headers("Set-Cookie").forEach { cookie ->
                cookieSet.add(cookie)
            }

            MMKVUtils.putValue(MMKVConstant.COOKIE, cookieSet)
        }
    }
}