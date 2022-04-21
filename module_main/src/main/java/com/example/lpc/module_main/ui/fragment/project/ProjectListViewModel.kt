package com.example.lpc.module_main.ui.fragment.project

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
 * Date: 2022/4/21
 * ClassName :ProjectListViewModel
 * Desc:
 */
class ProjectListViewModel(private val repository: ProjectRepository) :BaseViewModel() {

    private val _projectLiveData = MutableLiveData<PageVo<Article>>()
    val projectLiveData: LiveData<PageVo<Article>> = _projectLiveData

    fun getProjectList(page: Int = 0, id: String) {

        viewModelScope.launch {

            var result = repository.getProjectList(page, id)

            if (result is Results.Success) {

                _projectLiveData.postValue(result.data!!)

            }
        }
    }
}