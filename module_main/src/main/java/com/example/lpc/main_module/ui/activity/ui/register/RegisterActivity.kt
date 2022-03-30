package com.example.lpc.main_module.ui.activity.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import com.blankj.utilcode.util.ToastUtils
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.main_module.databinding.ActivityRegisterBinding

/**
 * 注册页面
 */
class RegisterActivity : BaseBindingActivity<ActivityRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModels { RegisterViewModelFactory() }

    override fun getViewBinding(): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewModel.registerResult.observe(this) { result ->
            binding.loading.visibility = View.GONE

            if (result.error != null) {
                ToastUtils.showShort(result.error)
            }

            if (result.success != null) {
                setResult(RESULT_OK)
                finish()
            }
        }

        viewModel.registerFrom.observe(this) {

            if (it.usernameError != null) {
                ToastUtils.showShort(it.usernameError)
            }

            if (it.passwordError != null) {
                ToastUtils.showShort(it.passwordError)
            }

            binding.registerBtn.isEnabled = it.isDataValid
        }

        binding.username.afterTextChanged {

            viewModel.registerDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString(),
                binding.rePassword.text.toString()
            )
        }

        binding.rePassword.afterTextChanged {
            viewModel.registerDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString(),
                binding.rePassword.text.toString()
            )
        }

        binding.rePassword.afterTextChanged {
            viewModel.registerDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString(),
                binding.rePassword.text.toString()
            )
        }

        //注册
        binding.registerBtn.setOnClickListener {

            binding.loading.visibility = View.VISIBLE

            viewModel.register(
                binding.username.text.toString(),
                binding.password.text.toString(),
                binding.rePassword.text.toString()
            )
        }
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {

            afterTextChanged.invoke(s.toString())

        }
    })

}