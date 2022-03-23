package com.example.lpc.main_module.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.main_module.R
import com.example.lpc.main_module.databinding.LayoutHomeBannerBinding
import com.example.lpc.main_module.ui.adapter.ArticleAdapter
import com.example.lpc.main_module.ui.adapter.ImageBannerAdapter
import com.example.lpc.main_module.ui.viewmodel.HomeViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.*
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
    private val adapter: ArticleAdapter = ArticleAdapter(mutableListOf())

    private val bannerAdapter: ImageBannerAdapter = ImageBannerAdapter(mutableListOf())

    override var layoutResId: Int = R.layout.fragment_home

    private var isRefresh: Boolean = false;


    override fun onCreate() {

        recyclerView.run {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@HomeFragment.adapter;
            itemAnimator = DefaultItemAnimator()
        }
        //刷新
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.getArticle(0)
                isRefresh = true;
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.getArticle(adapter.page)
            }
        })
        //设置Banner
        var binding = LayoutHomeBannerBinding.inflate(layoutInflater)
        adapter.setHeaderView(binding.root)

        binding.banner.run {
            //设置适配器
            setAdapter(bannerAdapter)
            //设置指示器
            indicator = CircleIndicator(requireActivity())
            //设置过度动画
            setPageTransformer(AlphaPageTransformer())
            //生命周期的添加
            addBannerLifecycleObserver(this@HomeFragment)

        }
    }

    override fun onLoadData() {
        //获取轮播图
        viewModel.getBanner()
        //获取文章
        viewModel.getArticle(0)

        //
        viewModel.bannerLiveData.observe(this, Observer {

            bannerAdapter.setDatas(it)

        })
        //
        viewModel.articleLiveData.observe(this, Observer {
            if (isRefresh) {
                adapter.setNewInstance(it)
                refreshLayout.finishRefresh();
                isRefresh = false;
            } else {
                adapter.addData(it)
                refreshLayout.finishLoadMore();
            }
        })
    }
}