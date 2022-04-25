package com.example.lpc.module_home.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.base.viewmodel.BaseViewModel
import com.example.lpc.lib_common.base.viewstate.ViewState
import com.example.lpc.lib_common.database.entity.KeyWord
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/3/31
 * ClassName :SearchViewModel
 * Desc:搜索
 */
class SearchViewModel(private val repository: SearchRepository) : BaseViewModel() {

    private val _resultData = MutableLiveData<PageVo<Article>>()
    val resultData: LiveData<PageVo<Article>> = _resultData

    private val _keywordData = MutableLiveData<MutableList<KeyWord>>()
    val keywordData: LiveData<MutableList<KeyWord>> = _keywordData

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState;

    fun search(page: Int = 0, keyword: String) {

        _viewState.value = ViewState(loading = true)

        viewModelScope.launch(Dispatchers.IO) {

            when (val result = repository.getSearchArticle(page, keyword)) {

                is Results.Success -> {
                    _resultData.postValue(result.data!!)

                    _viewState.postValue( ViewState(loading = false))
                }

                is Results.Failure -> {
                    _viewState.postValue(ViewState(loading = false, throwable = result.throwable))
                }
            }
            var keywordsList = repository.queryByKeyword(keyword)
            if (keywordsList.isEmpty()) {
                //插入数据库
                repository.insertKeyWord(keyword)
                //查询所有数据
                _keywordData.postValue(repository.getAllKeyWord())
            }
        }
    }

    fun getAllKeyword() {

        viewModelScope.launch(Dispatchers.IO) {

            _keywordData.postValue(repository.getAllKeyWord())
        }

    }

    fun delete(keyWord: KeyWord) {

        viewModelScope.launch(Dispatchers.IO) {

            repository.delete(keyWord)
        }
    }
}