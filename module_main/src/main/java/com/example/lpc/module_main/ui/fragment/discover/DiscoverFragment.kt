package com.example.lpc.module_main.ui.fragment.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.lpc.lib_common.base.fragment.BaseBindingFragment
import com.example.lpc.module_main.R
import com.example.lpc.module_main.databinding.FragmentDiscoverBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_discover.*

/**
 * Author: liupengchao
 * Date: 2022/4/18
 * ClassName :DiscoverFragment
 * Desc:
 */
class DiscoverFragment : BaseBindingFragment<FragmentDiscoverBinding>() {

    private val tabTitles by lazy { resources.getStringArray(R.array.discoverTabTitle) }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiscoverBinding {

        return FragmentDiscoverBinding.inflate(inflater, container, false)
    }

    override fun onCreate() {

        viewPager2.run {

            adapter = DiscoverFragmentAdapter(this@DiscoverFragment)

            registerOnPageChangeCallback(onPageChangeCallback)
        }

        //TabLayout 与 ViewPager2 绑定
        TabLayoutMediator(
            tabLayout,
            viewPager2
        ) { tab, position -> tab.text = tabTitles[position] }.attach()
    }

    override fun loadData() {

    }


    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
        }
    }
}