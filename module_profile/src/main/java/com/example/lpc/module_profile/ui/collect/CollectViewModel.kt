package com.example.lpc.module_profile.ui.collect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.base.viewmodel.BaseViewModel
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/6/14
 * ClassName :CollectViewModel
 * Desc:
 */
class CollectViewModel(private val repository: CollectListRepository) : BaseViewModel() {

    private val _collectLiveData = MutableLiveData<PageVo<Article>>()
    val collectLiveData: LiveData<PageVo<Article>> = _collectLiveData

    /**
     * 获取收藏列表
     * @param page Int
     */
    fun getCollectList(page: Int) {

        viewModelScope.launch {

            val result = repository.getCollectList(page)

            if (result is Results.Success) {
                _collectLiveData.value = result.data!!
            }
        }
    }
}