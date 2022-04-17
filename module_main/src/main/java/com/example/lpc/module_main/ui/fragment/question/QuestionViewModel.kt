package com.example.lpc.module_main.ui.fragment.question

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
 * Date: 2022/4/17
 * ClassName :QuestionViewModel
 * Desc:
 */
class QuestionViewModel(private val repository: QuestionRepository) : BaseViewModel() {

    private val _articleLiveData = MutableLiveData<PageVo<Article>>()

    val articleLiveData: LiveData<PageVo<Article>> = _articleLiveData

    fun getQuestion(page: Int) {

        viewModelScope.launch {

            val result = repository.getQuestion(page)

            if (result is Results.Success) {

                _articleLiveData.postValue(result.data)
            }
        }
    }
}