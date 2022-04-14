package com.example.lpc.module_main.ui.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.Navigation
import com.example.lpc.module_main.R
import com.example.lpc.module_main.ui.fragment.navigation.*
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.recyclerView
import kotlinx.android.synthetic.main.fragment_navigation.*

/**
 * Author: liupengchao
 * Date: 2022/3/18
 * ClassName :NavigationFragment
 * Desc:
 */
class NavigationFragment : BaseFragment() {

    private val adapter: NavigationAdapter = NavigationAdapter(mutableListOf())

    val viewModel by viewModels<NavigationViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NavigationViewModel(repository = NavigationRepository(dataSource = NavigationDataSource())) as T
            }
        }
    }

    override var layoutResId: Int = R.layout.fragment_navigation

    override fun onCreate() {

        viewModel.navigationData.observe(this) {

            adapter.setNewInstance(it)

            setSelectTagPosition(0)

        }

        recyclerView.run {

            layoutManager = LinearLayoutManager(requireContext())

            adapter = this@NavigationFragment.adapter
        }

        adapter.setOnItemClickListener(onItemClickListener)


    }

    override fun onLoadData() {
        //获取导航数据
        viewModel.getNavigation()
    }

    private val onItemClickListener =
        OnItemClickListener { adapter, view, position -> setSelectTagPosition(position) }

    /**
     * 设置导航选中的位置
     * @param position Int
     */
    private fun setSelectTagPosition(position: Int) {
        adapter?.run {
            val navigation = adapter.data[position] as Navigation
            setTagFlowLayout(navigation.articles)
            selectPosition = position
            notifyDataSetChanged()
        }
    }

    /**
     * 设置右侧标签
     * @param tags MutableList<Article>
     */
    private fun setTagFlowLayout(tags: MutableList<Article>) {
        tabFlowLayout.run {
            adapter = NavigationTagAdapter(tags = tags)

            setOnTagClickListener(object : TagFlowLayout.OnTagClickListener {
                override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {

                    ARouter.getInstance()
                        .build(ARouterConstant.Common.WEB)
                        .withSerializable(ParamsKeyConstant.ARTICLE,tags[position])
                        .navigation()

                    return true
                }
            })
        }
    }
}