package com.example.lpc.module_home.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Author: liupengchao
 * Date: 2022/3/31
 * ClassName :SearchViewModelFactory
 * Desc:
 */
class SearchViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return SearchViewModel(
            repository = SearchRepository(
                remoteDataSource = SearchRemoteDataSource(),
                localDataSource = SearchLocalDataSource()
            )
        ) as T
    }
}