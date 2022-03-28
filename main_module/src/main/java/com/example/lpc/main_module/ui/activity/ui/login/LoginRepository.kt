package com.example.lpc.main_module.ui.activity.ui.login

import com.example.lpc.lib_common.base.repository.BaseRepositoryBoth
import com.example.lpc.lib_common.http.Results
import com.example.lpc.main_module.ui.activity.ui.login.LoggedInUser
import com.example.lpc.main_module.ui.activity.ui.login.LoginLocalDataSource
import com.example.lpc.main_module.ui.activity.ui.login.LoginRemoteDataSource

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val loginRemoteDataSource: LoginRemoteDataSource,val loginLocalDataSource: LoginLocalDataSource):BaseRepositoryBoth<LoginRemoteDataSource,LoginLocalDataSource>(loginRemoteDataSource,loginLocalDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    suspend fun logout() {
        user = null
        loginRemoteDataSource.logout()
    }

   suspend fun login(username: String, password: String): Results<String> {

        return loginRemoteDataSource.login(username,password)
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}