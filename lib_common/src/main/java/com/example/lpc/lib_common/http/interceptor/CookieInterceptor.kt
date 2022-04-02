package com.example.lpc.lib_common.http.interceptor

import com.example.lpc.lib_common.constant.MMKVConstant
import com.example.lpc.lib_common.utils.MMKVUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author: liupengchao
 * Date: 2022/4/2
 * ClassName :CookieInterceptor
 * Desc:
 */
class CookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()

        val headers:MutableSet<String>? = MMKVUtils.getSetString(MMKVConstant.COOKIE)

        headers?.forEach {cookie->
            builder.addHeader("Cookie", cookie);
        }

        return chain.proceed(builder.build())
    }
}