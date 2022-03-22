package com.example.lpc.main_module.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.base.viewmodel.BaseViewModel
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.main_module.ui.repository.HomeRemoteDataSource
import com.example.lpc.main_module.ui.repository.HomeRepository
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :HomeViewModel
 * Desc:
 */
class HomeViewModel : BaseViewModel() {

    private val _articleLiveData = MutableLiveData<MutableList<Article>>()

    val articleLiveData: LiveData<MutableList<Article>> = _articleLiveData

    private val dataSource: HomeRemoteDataSource by lazy { HomeRemoteDataSource() }

    private val repository: HomeRepository by lazy { HomeRepository(dataSource) }


    fun getArticle(page: Int = 0) {

        viewModelScope.launch {

            val results: Results<PageVo<Article>> = repository.getArticle(page)

            when (results) {

                is Results.Success -> {
                    _articleLiveData.postValue(results.data.datas)
                }

                is Results.Failure -> {

                }
            }
        }
    }

}