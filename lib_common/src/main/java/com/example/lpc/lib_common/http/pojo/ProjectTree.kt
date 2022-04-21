package com.example.lpc.lib_common.http.pojo

import java.io.Serializable

/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ProjectTree
 * Desc:
 */
data class ProjectTree(

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
) : Serializable