package com.example.lpc.module_main.ui.activity.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.http.Errors
import com.example.lpc.lib_common.http.Results
import com.example.lpc.module_main.R
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/3/28
 * ClassName :RegisterViewModel
 * Desc:
 */
class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    private val _registerFrom = MutableLiveData<RegisterFormState>()
    val registerFrom: LiveData<RegisterFormState> = _registerFrom

    fun register(username: String, password: String, repassword: String) {

        viewModelScope.launch {

            var relust = repository?.register(username, password, repassword)

            when (relust) {

                is Results.Success -> {
                    _registerResult.value = RegisterResult(success = "注册成功");
                }

                is Results.Failure -> {

                    val throwable = relust.throwable as Errors.NetWorException

                    _registerResult.value = RegisterResult(error = throwable.errorMsg);
                }
            }
        }
    }

    fun registerDataChanged(username: String, password: String, repassword: String) {
        if (!isUserNameValid(username)) {
            _registerFrom.value = RegisterFormState(usernameError = R.string.invalid_username)
        } else if (!password.isNullOrBlank() && !isPasswordValid(password)) {
            _registerFrom.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else if (password != repassword) {
            _registerFrom.value =
                RegisterFormState(passwordError = R.string.invalid_password_repassword)
        } else {
            _registerFrom.value = RegisterFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

}