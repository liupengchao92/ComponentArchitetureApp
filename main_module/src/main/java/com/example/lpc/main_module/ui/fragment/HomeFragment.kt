package com.example.lpc.main_module.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.main_module.R
import com.example.lpc.main_module.ui.adapter.ArticleAdapter
import com.example.lpc.main_module.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author: liupengchao
 * Date: 2022/3/18
 * ClassName :HomeFragment
 * Desc:主页
 */
class HomeFragment : BaseFragment() {


    private val viewModel by viewModels<HomeViewModel>()

    //适配器
    private var adapter: ArticleAdapter = ArticleAdapter(mutableListOf())


    override var layoutResId: Int = R.layout.fragment_home


    override fun onCreate() {

        recyclerView.run {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@HomeFragment.adapter;
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onLoadData() {

        viewModel.getArticle(0)

        viewModel.articleLiveData.observe(this, Observer {

            adapter.setNewInstance(it)

        })
    }
}