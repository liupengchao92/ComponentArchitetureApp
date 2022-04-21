package com.example.lpc.module_main.ui.fragment.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.base.viewmodel.BaseViewModel
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.pojo.ProjectTree
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ProjectViewModel
 * Desc:
 */
class ProjectViewModel(private val repository: ProjectRepository) : BaseViewModel() {

    private val _treeLiveData = MutableLiveData<MutableList<ProjectTree>>()
    val treeLiveData: LiveData<MutableList<ProjectTree>> = _treeLiveData

    fun getProjectTree() {

        viewModelScope.launch {

            val result = repository.getProjectTree()

            if (result is Results.Success) {

                _treeLiveData.postValue(result.data!!)
            }
        }
    }
}