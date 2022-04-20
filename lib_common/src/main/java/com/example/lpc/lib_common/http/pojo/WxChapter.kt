package com.example.lpc.lib_common.http.pojo

/**
 * Author: liupengchao
 * Date: 2022/4/20
 * ClassName :WxChapter
 * Desc:
 */

data class WxChapter(

    val author: String,

    val children: MutableList<Article>,

    val cover: String,

    val desc: String,

    val id: String,

    val lisense: String,

    val lisenseLink: String,

    val name: String,

    val order: String,

    val parentChapterId: String,

    val userControlSetTop: Boolean,

    val visible: String
)