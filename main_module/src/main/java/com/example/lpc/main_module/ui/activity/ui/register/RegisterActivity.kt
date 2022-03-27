package com.example.lpc.main_module.ui.activity.ui.register

import android.os.Bundle
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.main_module.databinding.ActivityRegisterBinding

/**
 * 注册页面
 */
class RegisterActivity : BaseBindingActivity<ActivityRegisterBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewBinding(): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(layoutInflater)
    }
}