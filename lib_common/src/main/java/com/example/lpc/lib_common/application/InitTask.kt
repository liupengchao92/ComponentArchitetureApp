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
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk

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
class MMKVInitTask : IInitTask {

    override fun execute(application: Application) {

        var rootDir = MMKV.initialize(application)

        LogUtils.d("初始化:MMKV===============>>${rootDir}")
    }
}

@InitTask(name = "QbSdkInitTask")
class QbSdkInitTask : IInitTask {

    override fun execute(application: Application) {

        QbSdk.initX5Environment(application, object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
                LogUtils.d("初始化:QbSdk===============>>onCoreInitFinished")

            }

            override fun onViewInitFinished(p0: Boolean) {
                LogUtils.d("初始化:QbSdk===============>>onViewInitFinished:${p0}")

            }
        })
        //（可选）为了提高内核占比，在初始化前可配置允许移动网络下载内核（大小 40-50 MB）。默认移动网络不下载
        QbSdk.setDownloadWithoutWifi(true);
        //设置开启优化方案
        val map = HashMap<String, Boolean>()
        map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        QbSdk.initTbsSettings(map as Map<String, Any>?);
    }
}