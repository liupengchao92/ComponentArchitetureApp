package com.example.lpc.module_main.ui.fragment.square

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.module_main.R
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
                return SquareViewModel(repository = SquareRepository(datasource = SquareDatasource())) as T
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