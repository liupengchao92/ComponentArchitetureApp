package com.example.lpc.lib_common.extension

import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.http.BaseVo
import com.example.lpc.lib_common.http.Errors
import com.example.lpc.lib_common.http.Results
import retrofit2.Response

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :ResponseExt
 * Desc:处理请求结果
 */

inline fun <reified T> processApiResponse(response: () -> Response<BaseVo<T>>): Results<T> {

    return try {

        response.invoke().let {

            return if (it.isSuccessful) {

                if (it.body() != null) {

                    it.body()!!.let { baseVo ->

                        if (baseVo.errorCode == 0) {

                            if (baseVo.data == null) {
                                Results.success(T::class.java.newInstance())
                            } else {
                                Results.success(baseVo.data!!)
                            }

                        } else {

                            Results.failure(
                                Errors.NetWorException(
                                    baseVo.errorCode,
                                    baseVo.errorMsg
                                )
                            )

                        }
                    }

                } else {

                    Results.failure(Errors.EmptyResultsError())

                }

            } else {

                Results.failure(Errors.NetWorException(it.code(), it.errorBody().toString()))

            }
        }
    } catch (e: Throwable) {
        LogUtils.e("请求接口失败：" + e.message)
        return Results.failure(e)

    }

}