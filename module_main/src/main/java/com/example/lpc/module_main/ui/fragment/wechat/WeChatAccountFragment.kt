package com.example.lpc.module_main.ui.fragment.wechat

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.module_main.R
import com.example.lpc.module_main.databinding.LayoutWxChapterBinding
import com.example.lpc.module_main.ui.fragment.home.ArticleAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_wechat.*

/**
 * Author: liupengchao
 * Date: 2022/4/18
 * ClassName :WeChatAccountFragment
 * Desc:微信公众号
 */
class WeChatAccountFragment : BaseFragment() {

    private val adapter by lazy { ArticleAdapter() }

    private var chapterAdapter = ChapterAdapter()

    private val viewModel by viewModels<WeChatViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(WeChatViewModel::class.java)) {
                    return WeChatViewModel(repository = WeChatRepository(datasource = WeChatRemoteDatasource())) as T
                }
                throw IllegalArgumentException("no WeChatViewModel found")
            }
        }
    }

    override var layoutResId: Int = R.layout.fragment_wechat

    override fun onCreate() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {

            override fun onRefresh(refreshLayout: RefreshLayout) {
                adapter.isRefresh = true
                adapter.curPage = 0
                getChapterArticle(chapterAdapter.selectPosition)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                getChapterArticle(chapterAdapter.selectPosition)
            }
        })

        recyclerView.run {

            layoutManager = LinearLayoutManager(requireContext())

            adapter = this@WeChatAccountFragment.adapter

        }

        var chapterBinding = LayoutWxChapterBinding.inflate(layoutInflater)

        chapterBinding.chaptersRv.run {

            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

            adapter = chapterAdapter
        }
        adapter.run {
            addHeaderView(chapterBinding.root)
        }

        chapterAdapter.setOnItemClickListener { adapter, view, position ->
            chapterAdapter.selectPosition = position
            this.adapter.isRefresh = true
            this.adapter.curPage = 0
            getChapterArticle(position)
        }
    }

    override fun onLoadData() {

        viewModel.getWxChapters()

        viewModel.chapterLiveData.observe(this) {

            chapterAdapter.setNewInstance(it)

            getChapterArticle(chapterAdapter.selectPosition)
        }


        viewModel.articleLiveData.observe(this) {

            if (adapter.isRefresh) {
                adapter.setNewInstance(it.datas)
                smartRefreshLayout.finishRefresh()
            } else {
                adapter.addData(it.datas!!)
                smartRefreshLayout.finishLoadMore()
            }

            adapter.curPage = it.curPage
            adapter.isRefresh = false
        }
    }

    private fun getChapterArticle(position: Int) {
        if (!chapterAdapter.data.isNullOrEmpty()) {
            //获取文章列表
            viewModel.getWxArticle(page = adapter.curPage, id = chapterAdapter.data[position].id)
        }
    }
}