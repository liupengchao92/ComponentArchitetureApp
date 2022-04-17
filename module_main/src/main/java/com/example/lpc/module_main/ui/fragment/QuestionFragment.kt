package com.example.lpc.module_main.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.extension.binding
import com.example.lpc.module_main.R
import com.example.lpc.module_main.databinding.FragmentQuestionBinding
import com.example.lpc.module_main.ui.adapter.ArticleAdapter
import com.example.lpc.module_main.ui.fragment.question.QuestionRemoteDatasource
import com.example.lpc.module_main.ui.fragment.question.QuestionRepository
import com.example.lpc.module_main.ui.fragment.question.QuestionViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * Author: liupengchao
 * Date: 2022/3/18
 * ClassName :QuestionFragment
 * Desc:问答
 */
class QuestionFragment : BaseFragment() {

    private val binding by binding(FragmentQuestionBinding::bind)

    private val adapter = ArticleAdapter(mutableListOf())

    private var isRefresh: Boolean = false

    private val viewModel by viewModels<QuestionViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return QuestionViewModel(repository = QuestionRepository(datasource = QuestionRemoteDatasource())) as T
            }
        }
    }

    override var layoutResId: Int = R.layout.fragment_question

    override fun onCreate() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {

                viewModel.getQuestion(0)

                isRefresh = true

            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.getQuestion(adapter.page)

                isRefresh = false
            }
        })

        recyclerView.run {

            layoutManager = LinearLayoutManager(requireContext())

            adapter = this@QuestionFragment.adapter

        }
    }

    override fun onLoadData() {

        viewModel.getQuestion(0)
        
        viewModel.articleLiveData.observe(this) {
            if (isRefresh) {
                adapter.setNewInstance(it.datas)
                smartRefreshLayout.finishRefresh()
            } else {
                adapter.addData(it.datas!!)
                adapter.page = it.curPage + 1
                smartRefreshLayout.finishLoadMore()

            }
        }
    }
}