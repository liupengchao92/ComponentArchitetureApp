package com.example.lpc.module_main.ui.fragment.profile

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.module_main.R
import com.example.lpc.module_main.ui.fragment.project.PersonInfoManager
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Author: liupengchao
 * Date: 2022/3/18
 * ClassName :ProfileFragment
 * Desc:个人
 */
class ProfileFragment : BaseFragment(), View.OnClickListener {

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
            PersonInfoManager.personalInfo = it
            userNameTv.text = it.userInfo.username
        }
        messageLayout.setOnClickListener(this)
        integralLayout.setOnClickListener(this)
        favoriteLayout.setOnClickListener(this)
        shareLayout.setOnClickListener(this)
        settingLayout.setOnClickListener(this)
    }

    override fun onLoadData() {

        viewModel.getPersonInfo()
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.messageLayout -> {
                ToastUtils.showShort(R.string.my_message)
            }
            R.id.integralLayout -> {
                ToastUtils.showShort(R.string.my_integral)
            }
            R.id.favoriteLayout -> {
                ToastUtils.showShort(R.string.my_favorite)
                ARouter.getInstance().build(ARouterConstant.Profile.COLLECT_PATH).navigation()

            }
            R.id.shareLayout -> {
                ToastUtils.showShort(R.string.my_share)
            }
            R.id.settingLayout -> {
                ARouter.getInstance().build(ARouterConstant.Profile.SETTING_PATH).navigation()
            }
        }
    }
}