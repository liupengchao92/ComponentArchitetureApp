package com.example.lpc.componentachitecture.splash

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
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
        val animator = AnimatorSet()
        val scaleX = ObjectAnimator.ofFloat(welcome, "scaleX", 1f, 2f, 1f);
        val scaleY = ObjectAnimator.ofFloat(welcome, "scaleY", 1f, 2f, 1f);
        animator.duration = 1500;
        animator.interpolator = DecelerateInterpolator()
        animator.play(scaleX).with(scaleY);
        animator.start()
        animator.addonAnimationEnd {
            goToMain()
        }
    }

    private fun goToMain() {
        //跳转到主Activity

        var userName: String? = MMKVUtils.getString(MMKVConstant.USER_NAME)
        var password: String? = MMKVUtils.getString(MMKVConstant.PASSWORD)

        if (!userName.isNullOrBlank() && !password.isNullOrBlank()) {
            loginViewModel.login(userName, password)
        } else {
            //跳转到登录页面
            ARouter.getInstance().build(ARouterConstant.Main.LOGIN_PATH).navigation()
            finish()
        }

        loginViewModel.loginResult.observe(this) {

            if (it.success != null) {
                ARouter.getInstance()
                    .build(ARouterConstant.Main.INDEX_PATH)
                    .navigation()
            } else {
                //跳转到登录页面
                ARouter.getInstance()
                    .build(ARouterConstant.Main.LOGIN_PATH)
                    .navigation()
            }
            finish()
        }
    }
}

fun Animator.addonAnimationEnd(onAnimationEnd: (animation: Animator?) -> Unit) {

    this.addListener(object : Animator.AnimatorListener {

        override fun onAnimationStart(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            onAnimationEnd.invoke(animation)
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationRepeat(animation: Animator?) {
        }
    })
}