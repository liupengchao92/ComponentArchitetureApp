package com.example.lpc.lib_common.http.pojo

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :PageVo
 * Desc:
 */
class PageVo<T> {

    var curPage: Int = 0;

    var datas: MutableList<T>? = null

    var offset: Int = 0;

    var over: Boolean = false

    var pageCount: Int = 0

    var size: Int = 0

    var total: Int = 0

}