package com.example.lpc.module_main.application

import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.application.BaseApplication

/**
 * Author: liupengchao
 * Date: 2022/4/24
 * ClassName :ModuleMainApplication
 * Desc:
 */
class ModuleMainApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        LogUtils.e("ModuleMainApplication=======>>")
    }
}