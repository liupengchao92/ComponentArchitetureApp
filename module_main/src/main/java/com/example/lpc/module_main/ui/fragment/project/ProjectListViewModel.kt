package com.example.lpc.module_main.ui.fragment.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.module_main.ui.activity.ui.collect.CollectRepository
import com.example.lpc.module_main.ui.activity.ui.collect.CollectViewModel
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ProjectListViewModel
 * Desc:
 */
class ProjectListViewModel(
    private val repository: ProjectRepository,
    private val collectRepository: CollectRepository
) : CollectViewModel(collectRepository) {

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