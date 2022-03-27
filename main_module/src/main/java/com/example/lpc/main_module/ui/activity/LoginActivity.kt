package com.example.lpc.main_module.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.main_module.R
import com.example.lpc.main_module.databinding.ActivityLoginBinding
import com.example.lpc.main_module.ui.viewmodel.LoginViewModel

@Route(path = ARouterConstant.Main.LOGIN_PATH)
class LoginActivity : BaseBindingActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //忘记密码
        binding.forgetTv.setOnClickListener {

            ToastUtils.showShort(R.string.forget_password)
        }
        //登录
        binding.loginBtn.setOnClickListener {
            var userName = binding.usernameEt.text.toString().trim()
            if (userName.isNullOrEmpty()) {
                ToastUtils.showShort("请输入用户名")
                return@setOnClickListener
            }

            var password = binding.passwordEt.text.toString().trim()
            if (password.isNullOrEmpty()) {
                ToastUtils.showShort("请输入密码")
                return@setOnClickListener
            }

            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}