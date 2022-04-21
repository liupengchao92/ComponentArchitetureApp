package com.example.lpc.module_main.ui.fragment.project

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.CloneUtils
import com.blankj.utilcode.util.ColorUtils
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.http.pojo.ProjectTree
import com.example.lpc.module_main.R
import com.example.lpc.module_main.ui.utils.MagicIndicatorBindHelper
import kotlinx.android.synthetic.main.fragment_project.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView


/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ProjectFragment
 * Desc:
 * 项目
 */
class ProjectFragment : BaseFragment() {

    private var tabTitles = mutableListOf<ProjectTree>()

    private val pagerAdapter: ProjectFragmentPagerAdapter by lazy { ProjectFragmentPagerAdapter(this) }

    private val viewModel by viewModels<ProjectViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ProjectViewModel(repository = ProjectRepository(datasource = ProjectDatasource())) as T
            }
        }
    }

    override var layoutResId: Int = R.layout.fragment_project

    override fun onCreate() {

        viewPager2.run {

            adapter = pagerAdapter
        }

        MagicIndicatorBindHelper.bind(magic_indicator, viewPager2)
    }

    override fun onLoadData() {

        viewModel.getProjectTree()

        viewModel.treeLiveData.observe(this) {

            tabTitles = it

            pagerAdapter.setTrees(it)

            val navigator = CommonNavigator(requireContext())
            navigator.adapter = navigatorAdapter
            magic_indicator.navigator = navigator
        }
    }

    private val navigatorAdapter = object : CommonNavigatorAdapter() {

        override fun getCount(): Int = tabTitles.size

        override fun getTitleView(p0: Context?, position: Int): IPagerTitleView {
            return ColorTransitionPagerTitleView(context).apply {
                normalColor = ColorUtils.getColor(R.color.whitesmoke)
                selectedColor = Color.WHITE
                text = tabTitles[position].name
                textSize = 15F

                setOnClickListener {

                    viewPager2.setCurrentItem(position, false)
                }
            }
        }

        override fun getIndicator(p0: Context?): IPagerIndicator {
            val indicator = LinePagerIndicator(context)
            indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                indicator.setColors(Color.WHITE)
            return indicator
        }
    }

}