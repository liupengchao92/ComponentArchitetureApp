package com.example.lpc.module_main.ui.activity.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ActivityUtils
import com.example.lpc.lib_common.http.Errors
import com.example.lpc.lib_common.http.Results
import com.example.lpc.module_main.R
import kotlinx.coroutines.launch


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {

        viewModelScope.launch {

            val result = loginRepository?.login(username, password)

            if (result is Results.Success) {
                _loginResult.value =
                    LoginResult(success =  result.data)

            } else {

                if ((result as Results.Failure).throwable is Errors) {

                    val error: Errors = (result as Results.Failure).throwable as Errors

                    if (error is Errors.NetWorException) {

                        _loginResult.value = LoginResult(error = error.errorMsg)

                    }
                } else {
                    _loginResult.value = LoginResult(
                        error = ActivityUtils.getTopActivity().getString(R.string.login_failed)
                    )
                }
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
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
        return password.isNotEmpty() && password.length > 5
    }
}