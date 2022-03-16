package com.example.lpc.componentachitecture.splash

import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lpc.componentachitecture.R
import com.example.lpc.lib_common.base.BaseActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import kotlinx.android.synthetic.main.activity_splash.*

@Route(path = ARouterConstant.App.SPLASH_PATH)
class SplashActivity : BaseActivity() {

    override var layoutResId = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gotoMain.setOnClickListener {
            //跳转到主Activity
            ARouter.getInstance().build(ARouterConstant.Main.SECOND_PATH).navigation()

        }
    }
}