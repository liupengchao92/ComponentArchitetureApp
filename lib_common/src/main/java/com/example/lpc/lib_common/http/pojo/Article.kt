package com.example.lpc.lib_common.http.pojo

import java.io.Serializable

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :Article
 * Desc:文章实体
 */
class Article :Serializable {

    var apkLink: String? = null

    var audit = 0

    var author: String? = null

    var canEdit = false

    var chapterId: String? = null

    var chapterName: String? = null

    var collect = false

    var courseId: String? = null

    var desc: String? = null

    var descMd: String? = null

    var envelopePic: String? = null

    var fresh = false

    var host: String? = null

    var id: String? = null

    var link: String? = null

    var niceDate: String? = null

    var niceShareDate: String? = null

    var origin: String? = null

    var prefix: String? = null

    var projectLink: String? = null

    var publishTime: Long = 0

    var realSuperChapterId: String? = null

    var selfVisible = 0

    var shareDate: Long = 0

    var shareUser: String? = null

    var superChapterId: String? = null

    var superChapterName: String? = null

    var tags: List<Tags>? = null

    var title: String? = null

    var type = 0

    var userId: String? = null

    var visible = 0

    var zan = 0
}