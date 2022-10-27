package com.example.lpc.module_main.service

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.http.pojo.PersonalInfo
import com.example.lpc.lib_common.service.CollectService
import com.example.lpc.module_main.ui.activity.MainActivity
import com.example.lpc.module_main.ui.fragment.project.PersonInfoManager

/**
 * Author: liupengchao
 * Date: 2022/6/13
 * ClassName :CollectServiceImpl
 * Desc:
 */
@Route(path = ARouterConstant.Service.SERVICE_COLLECT)
class CollectServiceImpl : CollectService {

    override fun getPersonInfo(): PersonalInfo? {

       /* val activityList = ActivityUtils.getActivityList()

        for (activity in activityList) {

            if (activity.javaClass.simpleName == MainActivity::class.java.simpleName) {
                (activity as FragmentActivity).supportFragmentManager?.let {

                    val fragments = it.fragments

                    fragments.forEach { fragment ->

                        if (fragment is NavHostFragment){

                            fragment.
                        }
                    }

                    LogUtils.d("CollectServiceImpl======>>getPersonInfo${fragments}")

                }
            }
        }
*/
        return PersonInfoManager.personalInfo
    }

    override fun init(context: Context?) {
        LogUtils.d("CollectServiceImpl======>>init${context}")

    }
}