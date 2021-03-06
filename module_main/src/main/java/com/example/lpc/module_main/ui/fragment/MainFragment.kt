package com.example.lpc.module_main.ui.fragment

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.base.fragment.BaseBindingFragment
import com.example.lpc.lib_common.constant.CommonConstant.MainFrameworkPage.DISCOVER
import com.example.lpc.lib_common.constant.CommonConstant.MainFrameworkPage.HOME
import com.example.lpc.lib_common.constant.CommonConstant.MainFrameworkPage.NAVIGATION
import com.example.lpc.lib_common.constant.CommonConstant.MainFrameworkPage.PROFILE
import com.example.lpc.lib_common.constant.CommonConstant.MainFrameworkPage.PROJECT
import com.example.lpc.module_main.R
import com.example.lpc.module_main.databinding.FragmentMainBinding
import com.example.lpc.module_main.ui.fragment.discover.DiscoverFragment
import com.example.lpc.module_main.ui.fragment.home.HomeFragment
import com.example.lpc.module_main.ui.fragment.navigation.NavigationFragment
import com.example.lpc.module_main.ui.fragment.profile.ProfileFragment
import com.example.lpc.module_main.ui.fragment.project.ProjectFragment
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Author: liupengchao
 * Date: 2022/3/17
 * ClassName :MainFragment
 * Desc:
 */
class MainFragment : BaseBindingFragment<FragmentMainBinding>() {

    private val viewModel  by viewModels<MainViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onCreate() {
        BarUtils.addMarginTopEqualStatusBarHeight(statusBarView)
        //默认选中第一个
        setCurrentPage(viewModel.currentPage.value!!)
        //添加监听
        binding.bottomNavigationView.setOnItemSelectedListener(onItemClickListener)

    }

    override fun loadData() {


    }

    private val onItemClickListener = NavigationBarView.OnItemSelectedListener { item ->
        when (item.itemId) {

            R.id.navi_home -> {
                setCurrentPage(HOME)
            }

            R.id.navi_discover -> {
                setCurrentPage(DISCOVER)
            }

            R.id.navi_navigation -> {
                setCurrentPage(NAVIGATION)
            }

            R.id.navi_project -> {
                setCurrentPage(PROJECT)
            }

            R.id.navi_profile -> {
                setCurrentPage(PROFILE)
            }
        }
        true
    }


    private fun setCurrentPage(page: String) {

        viewModel.currentPage.value = page;

        childFragmentManager?.let {

            var beginTransaction = childFragmentManager.beginTransaction()

            //隐藏所有的Fragment
            it.fragments.forEach { fragment ->
                beginTransaction.hide(fragment)
            }

            when (page) {
                HOME -> {
                    var homeFragment = it.findFragmentByTag(page)

                    if (homeFragment == null) {

                        homeFragment = HomeFragment()

                        beginTransaction.add(R.id.contentContainer, homeFragment, page)

                    } else {
                        beginTransaction.show(homeFragment)
                    }

                    setStatusBarLightMode(false)
                }

                DISCOVER -> {
                    var discoverFragment = it.findFragmentByTag(page)

                    if (discoverFragment == null) {

                        discoverFragment = DiscoverFragment()

                        beginTransaction.add(R.id.contentContainer, discoverFragment, page)

                    } else {
                        beginTransaction.show(discoverFragment)
                    }

                    setStatusBarLightMode(false)
                }

                NAVIGATION -> {
                    var navigationFragment = it.findFragmentByTag(page)

                    if (navigationFragment == null) {

                        navigationFragment = NavigationFragment()

                        beginTransaction.add(R.id.contentContainer, navigationFragment, page)

                    } else {
                        beginTransaction.show(navigationFragment)
                    }

                    setStatusBarLightMode(false)
                }

                PROJECT -> {
                    var projectFragment = it.findFragmentByTag(page)

                    if (projectFragment == null) {

                        projectFragment = ProjectFragment()

                        beginTransaction.add(R.id.contentContainer, projectFragment, page)

                    } else {
                        beginTransaction.show(projectFragment)
                    }

                    setStatusBarLightMode(false)
                }

                PROFILE -> {
                    var profileFragment = it.findFragmentByTag(page)

                    if (profileFragment == null) {

                        profileFragment = ProfileFragment()

                        beginTransaction.add(R.id.contentContainer, profileFragment, page)

                    } else {
                        beginTransaction.show(profileFragment)
                    }

                    setStatusBarLightMode(true)
                }
            }
            //提交事务
            beginTransaction.commit()
        }
    }

    private fun setStatusBarLightMode(isLightMode: Boolean) {
        BarUtils.setStatusBarColor(
            requireActivity(),
            ColorUtils.getColor(if (isLightMode) R.color.white else R.color.colorPrimary)
        )
        BarUtils.setStatusBarLightMode(requireActivity(), isLightMode)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        LogUtils.d("onConfigurationChanged===================>>")
    }
}