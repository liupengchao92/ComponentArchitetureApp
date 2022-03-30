package com.example.lpc.module_main.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.base.viewmodel.BaseViewModel
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.Banner
import com.example.lpc.lib_common.http.pojo.HotKey
import com.example.lpc.module_main.ui.repository.HomeRemoteDataSource
import com.example.lpc.module_main.ui.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author: liupengchao
 * Date: 2022/3/21
 * ClassName :HomeViewModel
 * Desc:
 */
class HomeViewModel : BaseViewModel() {

    private val dataSource: HomeRemoteDataSource by lazy { HomeRemoteDataSource() }

    private val repository: HomeRepository by lazy { HomeRepository(dataSource) }

    private val _articleLiveData = MutableLiveData<MutableList<Article>>()
    val articleLiveData: LiveData<MutableList<Article>> = _articleLiveData

    private val _bannerLiveData = MutableLiveData<MutableList<Banner>>()
    val bannerLiveData: LiveData<MutableList<Banner>> = _bannerLiveData

    private val _hotKeyData = MutableLiveData<MutableList<HotKey>>()
    val hotKeyData: LiveData<MutableList<HotKey>> = _hotKeyData


    /**
     * 获取文章
     * @param page Int
     */
    fun getArticle(page: Int = 0) {

        viewModelScope.launch {

            if (page == 0) {

                val articleList = withContext(Dispatchers.IO) {

                    val list = mutableListOf<Article>()

                    val topAsync = async { repository.getTop() }
                    val articleAsync = async { repository.getArticle(page) }

                    val topArticle = topAsync.await()
                    val article = articleAsync.await()

                    if (topArticle is Results.Success) {
                        list.addAll(topArticle.data)
                    }
                    if (article is Results.Success) {
                        list.addAll(article.data.datas!!)
                    }
                    list
                }

                _articleLiveData.postValue(articleList)

            } else {
                when (val result = repository.getArticle(page)) {

                    is Results.Success -> {
                        _articleLiveData.postValue(result.data.datas)
                    }

                    is Results.Failure -> {

                    }
                }
            }
        }
    }

    /**
     * 获取轮播图
     */
    fun getBanner() {

        viewModelScope.launch {

            when (val result = repository.getBanner()) {

                is Results.Success -> {
                    _bannerLiveData.postValue(result.data!!)
                }

                is Results.Failure -> {

                }
            }
        }
    }

    /**
     *
     * 获取搜索热词
     */
    fun getSearchHotKey() {

        viewModelScope.launch {

            when (val result = repository.getSearchHotKey()) {

                is Results.Success -> {
                    _hotKeyData.postValue(result.data!!)
                }

                is Results.Failure -> {

                }
            }
        }
    }
}