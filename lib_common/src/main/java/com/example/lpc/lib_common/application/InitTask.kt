package com.example.lpc.lib_common.application

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.example.lpc.annotation.InitTask
import com.example.lpc.api.IInitTask
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
import com.tencent.mmkv.MMKV

/**
 * Author: liupengchao
 * Date: 2022/3/28
 * ClassName :InitTask
 * Desc:
 */

//初始化刷新控件
@InitTask(name = "SmartRefreshLayoutInitTask", background = false)
class SmartRefreshLayoutInitTask : IInitTask {

    override fun execute(application: Application) {

        //头布局
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(
                context: Context,
                layout: RefreshLayout
            ): RefreshHeader {
                // layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色

                return ClassicsHeader(context)
            }
        })

        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            override fun createRefreshFooter(
                context: Context,
                layout: RefreshLayout
            ): RefreshFooter {
                return ClassicsFooter(context)
            }
        })

        LogUtils.d("初始化:SmartRefreshLayout===============>>")
    }
}

//MMKV
@InitTask(name = "MMKVInitTask", background = false)
class MMKVInitTask() : IInitTask {

    override fun execute(application: Application) {

        var rootDir = MMKV.initialize(application)

        LogUtils.d("初始化:MMKV===============>>${rootDir}")
    }
}