package com.example.lpc.module_main.ui.fragment.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.base.viewmodel.BaseViewModel
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Navigation
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/4/14
 * ClassName :NavigationViewModel
 * Desc:
 */
class NavigationViewModel(private val repository: NavigationRepository) : BaseViewModel() {

    private val _navigationData = MutableLiveData<MutableList<Navigation>>()
    val navigationData: LiveData<MutableList<Navigation>> = _navigationData

    fun getNavigation() {

        viewModelScope.launch {

            var result = repository.getNavigation()

            if (result is Results.Success) {

                _navigationData.postValue(result.data!!)
            }
        }
    }
}