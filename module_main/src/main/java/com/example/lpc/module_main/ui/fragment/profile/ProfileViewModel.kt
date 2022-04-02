package com.example.lpc.module_main.ui.fragment.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.example.lpc.lib_common.http.Errors
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.PersonalInfo
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/4/1
 * ClassName :ProfileViewModel
 * Desc:
 */
class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _userInfoData = MutableLiveData<PersonalInfo>()

    val userInfoData: LiveData<PersonalInfo> = _userInfoData

    fun getPersonInfo() {

        viewModelScope.launch {

            when (val result = repository.getUserInfo()) {

                is Results.Success -> {

                    _userInfoData.postValue(result.data!!)
                }

                is Results.Failure -> {

                    when (result.throwable) {

                        is Errors.NetWorException -> {

                            ToastUtils.showShort((result.throwable as Errors.NetWorException).errorMsg)
                        }
                    }
                }
            }
        }
    }
}