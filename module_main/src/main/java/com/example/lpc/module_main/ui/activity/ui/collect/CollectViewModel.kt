package com.example.lpc.module_main.ui.activity.ui.collect

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.example.lpc.lib_common.base.viewmodel.BaseViewModel
import com.example.lpc.lib_common.http.Results
import com.example.lpc.module_main.R
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/4/22
 * ClassName :CollectViewModel
 * Desc:收藏ViewModel
 */
open class CollectViewModel(private val repository: CollectRepository) : BaseViewModel() {

    fun collect(id: String) {

        viewModelScope.launch {

            if (repository.collectArticle(id) is Results.Success) {
                ToastUtils.showShort(R.string.collect_success)
            } else {
                ToastUtils.showShort(R.string.collect_failure)
            }
        }
    }

    fun cancelCollect(id: String) {

        viewModelScope.launch {
            if (repository.cancelCollectArticle(id) is Results.Success) {
                ToastUtils.showShort(R.string.cancel_success)
            } else {
                ToastUtils.showShort(R.string.cancel_failure)
            }
        }
    }
}