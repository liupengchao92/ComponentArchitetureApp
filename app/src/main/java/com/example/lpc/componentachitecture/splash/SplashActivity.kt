package com.example.lpc.componentachitecture.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lpc.componentachitecture.R
import com.example.lpc.lib_common.base.activity.BaseActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.constant.MMKVConstant
import com.example.lpc.lib_common.utils.MMKVUtils
import com.example.lpc.module_main.ui.activity.ui.login.LoginViewModel
import com.example.lpc.module_main.ui.activity.ui.login.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_splash.*

@Route(path = ARouterConstant.App.SPLASH_PATH)
class SplashActivity : BaseActivity() {

    private val loginViewModel: LoginViewModel by viewModels { LoginViewModelFactory() }

    override var layoutResId = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gotoMain.setOnClickListener {
            //跳转到主Activity

            var userName: String? = MMKVUtils.getString(MMKVConstant.USER_NAME)
            var password: String? = MMKVUtils.getString(MMKVConstant.PASSWORD)

            if (!userName.isNullOrBlank() && !password.isNullOrBlank()) {
                loginViewModel.login(userName, password)
            } else {
                //跳转到登录页面
                ARouter.getInstance().build(ARouterConstant.Main.LOGIN_PATH).navigation()
            }

            loginViewModel.loginResult.observe(this) {

                if (it.success != null) {
                    ARouter.getInstance().build(ARouterConstant.Main.INDEX_PATH).navigation()
                } else {
                    //跳转到登录页面
                    ARouter.getInstance().build(ARouterConstant.Main.LOGIN_PATH).navigation()
                }
            }
        }
    }
}