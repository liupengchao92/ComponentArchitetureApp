package com.example.lpc.main_module.ui.activity

import android.app.StatusBarManager
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.DeviceUtils
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.main_module.databinding.ActivityLoginBinding

@Route(path = ARouterConstant.Main.LOGIN_PATH)
class LoginActivity : BaseBindingActivity<ActivityLoginBinding>() {

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}