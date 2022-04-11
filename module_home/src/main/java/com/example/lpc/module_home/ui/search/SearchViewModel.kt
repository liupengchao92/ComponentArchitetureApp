package com.example.lpc.module_home.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _resultData = MutableLiveData<PageVo<Article>>()
    val resultData: LiveData<PageVo<Article>> = _resultData

    private val _keywordData = MutableLiveData<MutableList<KeyWord>>()
    val keywordData: LiveData<MutableList<KeyWord>> = _keywordData

    fun search(page: Int = 0, keyword: String) {

        viewModelScope.launch(Dispatchers.IO) {

            when (val result = repository.getSearchArticle(page, keyword)) {

                is Results.Success -> {
                    _resultData.postValue(result.data!!)
                }

                is Results.Failure -> {

                }
            }
            var keywordsList = repository.queryByKeyword(keyword)
            if (keywordsList.isEmpty()){
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