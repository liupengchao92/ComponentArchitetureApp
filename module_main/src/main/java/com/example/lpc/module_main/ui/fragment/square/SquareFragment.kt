package com.example.lpc.module_main.ui.fragment.square

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.extension.collectAnimation
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.module_main.R
import com.example.lpc.module_main.ui.activity.ui.collect.CollectDataSource
import com.example.lpc.module_main.ui.activity.ui.collect.CollectRepository
import com.example.lpc.module_main.ui.fragment.square.paging.ArticlePagingAdapter
import kotlinx.android.synthetic.main.fragment_square.*
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/4/18
 * ClassName :SquareFragment
 * Desc:广场
 */
class SquareFragment : BaseFragment() {

    private val pagingAdapter = ArticlePagingAdapter()

    private val viewModel by viewModels<SquareViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SquareViewModel(
                    repository = SquareRepository(datasource = SquareDatasource()),
                    collectRepository = CollectRepository(dataSource = CollectDataSource())
                ) as T
            }
        }
    }

    override var layoutResId: Int = R.layout.fragment_square


    override fun onCreate() {

        smartRefreshLayout.setOnRefreshListener {
            pagingAdapter.refresh()
        }
        recyclerView.run {

            layoutManager = LinearLayoutManager(requireContext())

            adapter = pagingAdapter
        }

        //加载状态
        pagingAdapter.loadStateFlow.asLiveData().observe(this) { loadState ->

            if (loadState.refresh is LoadState.NotLoading) {
                smartRefreshLayout.finishRefresh()
            }
        }

        pagingAdapter.onItemClickListener = object : ArticlePagingAdapter.OnItemClickListener {
            override fun onItemChildClick(
                adapter: PagingDataAdapter<*, *>,
                view: View,
                position: Int
            ) {
                val article: Article = (adapter as ArticlePagingAdapter).getItemData(position)!!
                //收藏
                if (article.collect) {

                    (view as ImageView).setImageResource(R.drawable.ic_un_like)

                    viewModel.cancelCollect(article.id!!)

                } else {
                    (view as ImageView).setImageResource(R.drawable.ic_like)
                    //收藏文章
                    viewModel.collect(article.id!!)
                    //执行动画
                    view.collectAnimation()
                }

                article.collect = !article.collect

                adapter.notifyItemChanged(position)
            }
        }
    }

    override fun onLoadData() {


        viewModel.flow.asLiveData().observe(this) { pagingData ->

            viewLifecycleOwner.lifecycleScope.launch {
                pagingAdapter.submitData(pagingData)
            }
        }

        /*viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }*/
    }
}