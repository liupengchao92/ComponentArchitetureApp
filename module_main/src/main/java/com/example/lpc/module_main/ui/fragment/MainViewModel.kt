package com.example.lpc.module_main.ui.fragment

import androidx.lifecycle.MutableLiveData
import com.example.lpc.lib_common.base.viewmodel.BaseViewModel
import com.example.lpc.lib_common.constant.CommonConstant

/**
 * Author: liupengchao
 * Date: 2022/6/10
 * ClassName :MainViewModel
 * Desc:
 */
class MainViewModel : BaseViewModel() {

    val currentPage = MutableLiveData<String>(CommonConstant.MainFrameworkPage.HOME)

}