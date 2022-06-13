package com.example.lpc.module_profile.ui.collect

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.service.CollectService
import com.example.lpc.module_profile.databinding.ActivityCollectBinding

@Route(path = ARouterConstant.Profile.COLLECT_PATH)
class CollectActivity : BaseBindingActivity<ActivityCollectBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val collectService:CollectService = ARouter.getInstance().build(ARouterConstant.Service.SERVICE_COLLECT).navigation() as CollectService

        val personInfo = collectService.getPersonInfo()

        LogUtils.d("personInfo : $personInfo")
    }

    override fun getViewBinding(): ActivityCollectBinding {
        return ActivityCollectBinding.inflate(layoutInflater)
    }
}