package com.example.lpc.module_main.ui.fragment

import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.SizeUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.constant.ParamsKeyConstant.CURRENT_HOT_KEY
import com.example.lpc.lib_common.constant.ParamsKeyConstant.HOT_KEY_LIST
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.HotKey
import com.example.lpc.module_main.R
import com.example.lpc.module_main.databinding.LayoutHomeBannerBinding
import com.example.lpc.module_main.ui.activity.ui.web.CommonWebActivity
import com.example.lpc.module_main.ui.adapter.ArticleAdapter
import com.example.lpc.module_main.ui.adapter.ImageBannerAdapter
import com.example.lpc.module_main.ui.viewmodel.HomeViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

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

    private var hotKeyList = mutableListOf<HotKey>()


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

        //跳转到搜索页面
        viewFlipper.setOnClickListener {
            var currentKey = (viewFlipper.currentView as TextView).text.toString()

            ARouter.getInstance()
                .build(ARouterConstant.Home.SEARCH_PATH)
                .withString(CURRENT_HOT_KEY, currentKey)
                .withSerializable(HOT_KEY_LIST, hotKeyList as ArrayList<HotKey>)
                .navigation()
        }
    }

    override fun onLoadData() {
        //获取搜索热词
        viewModel.getSearchHotKey()
        //获取轮播图
        viewModel.getBanner()
        //获取文章
        viewModel.getArticle(0)


        viewModel.hotKeyData.observe(this) { hotKeyList ->
            this.hotKeyList = hotKeyList

            var text: TextView
            var params: FrameLayout.LayoutParams
            hotKeyList.forEach {
                text = TextView(requireContext())
                text.text = it.name
                text.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                params = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
                params.gravity = Gravity.CENTER_VERTICAL
                params.leftMargin = SizeUtils.dp2px(10f)

                viewFlipper.addView(text, params)

            }

            viewFlipper.startFlipping()
        }

        viewModel.bannerLiveData.observe(this, Observer {

            bannerAdapter.setDatas(it)

        })

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