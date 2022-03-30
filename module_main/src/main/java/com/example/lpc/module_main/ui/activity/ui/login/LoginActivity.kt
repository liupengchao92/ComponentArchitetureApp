package com.example.lpc.module_main.ui.activity.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.constant.MMKVConstant
import com.example.lpc.lib_common.http.pojo.LoginUserInfo
import com.example.lpc.lib_common.utils.MMKVUtils
import com.example.lpc.module_main.R
import com.example.lpc.module_main.databinding.ActivityLoginBinding
import com.example.lpc.module_main.ui.activity.MainActivity
import com.example.lpc.module_main.ui.activity.ui.register.RegisterActivity

/**
 * 登录页面
 */
@Route(path = ARouterConstant.Main.LOGIN_PATH)
class LoginActivity : BaseBindingActivity<ActivityLoginBinding>() {

    private val loginViewModel: LoginViewModel by viewModels { LoginViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        loginViewModel.loginFormState.observe(this, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.loginBtn.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.usernameEt.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.passwordEt.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this, Observer {
            val loginResult = it ?: return@Observer

            binding.loading.visibility = View.GONE

            //登录失败
            if (!loginResult.error.isNullOrBlank()) {
                showLoginFailed(loginResult.error!!)
            }
            //登录成功
            if (loginResult.success != null) {

                var loginUserInfo = loginResult.success

                //保存用户名和密码
                MMKVUtils.putValue(MMKVConstant.USER_NAME, binding.usernameEt.text.toString())
                MMKVUtils.putValue(MMKVConstant.PASSWORD, binding.passwordEt.text.toString())

                updateUiWithUser(loginResult.success)
                setResult(Activity.RESULT_OK)
                //Complete and destroy login activity once successful
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        })

        binding.usernameEt.afterTextChanged {
            loginViewModel.loginDataChanged(
                binding.usernameEt.text.toString(),
                binding.passwordEt.text.toString()
            )
        }

        binding.passwordEt.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    binding.usernameEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            binding.usernameEt.text.toString(),
                            binding.passwordEt.text.toString()
                        )
                }
                false
            }

            binding.loginBtn.setOnClickListener {
                binding.loading.visibility = View.VISIBLE
                loginViewModel.login(
                    binding.usernameEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
            }
        }

        binding.registerTv.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun updateUiWithUser(model: LoginUserInfo) {
        val welcome = getString(R.string.welcome)
        val displayName = model.username
        ToastUtils.showShort("$welcome $displayName")
    }

    private fun showLoginFailed(errorString: String) {
        ToastUtils.showShort(errorString)
    }

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}