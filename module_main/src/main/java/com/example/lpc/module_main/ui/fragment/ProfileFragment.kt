package com.example.lpc.module_main.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.module_main.R
import com.example.lpc.module_main.ui.fragment.profile.ProfileDataSource
import com.example.lpc.module_main.ui.fragment.profile.ProfileRepository
import com.example.lpc.module_main.ui.fragment.profile.ProfileViewModel

/**
 * Author: liupengchao
 * Date: 2022/3/18
 * ClassName :ProfileFragment
 * Desc:
 */
class ProfileFragment : BaseFragment() {

    private val viewModel: ProfileViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ProfileViewModel(repository = ProfileRepository(dataSource = ProfileDataSource())) as T
            }
        }
    }

    override var layoutResId: Int = R.layout.fragment_profile


    override fun onCreate() {

        viewModel.userInfoData.observe(this) {

        }
    }

    override fun onLoadData() {

        viewModel.getPersonInfo()
    }
}