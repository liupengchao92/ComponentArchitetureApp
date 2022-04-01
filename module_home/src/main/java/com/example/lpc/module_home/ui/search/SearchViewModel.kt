package com.example.lpc.module_home.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/3/31
 * ClassName :SearchViewModel
 * Desc:搜索
 */
class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _resultData = MutableLiveData<PageVo<Article>>()
    val resultData: LiveData<PageVo<Article>> = _resultData

    fun search(page: Int = 0, keyword: String) {

        viewModelScope.launch {

            when (val result = repository.getSearchArticle(page, keyword)) {

                is Results.Success -> {
                    _resultData.postValue(result.data!!)
                }

                is Results.Failure -> {

                }
            }
        }
    }
}