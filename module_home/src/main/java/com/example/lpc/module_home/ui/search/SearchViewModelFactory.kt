package com.example.lpc.module_home.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lpc.lib_common.database.DatabaseManager
import com.example.lpc.lib_common.database.dao.KeywordDao

/**
 * Author: liupengchao
 * Date: 2022/3/31
 * ClassName :SearchViewModelFactory
 * Desc:
 */
class SearchViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val dao: KeywordDao = DatabaseManager.INSTANCE.getKeyWordDao()

        return SearchViewModel(
            repository = SearchRepository(
                remoteDataSource = SearchRemoteDataSource(),
                localDataSource = SearchLocalDataSource(dao = dao)
            )
        ) as T
    }
}