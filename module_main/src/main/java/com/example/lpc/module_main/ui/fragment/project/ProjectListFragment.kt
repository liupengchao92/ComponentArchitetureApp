package com.example.lpc.module_main.ui.fragment.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.extension.collectAnimation
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.ProjectTree
import com.example.lpc.module_main.R
import com.example.lpc.module_main.ui.activity.ui.collect.CollectDataSource
import com.example.lpc.module_main.ui.activity.ui.collect.CollectRepository
import com.example.lpc.module_main.ui.activity.ui.web.CommonWebActivity
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_project_list.*

/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ProjectListFragment
 * Desc:
 */
class ProjectListFragment : BaseFragment() {

    private val adapter: ProjectListAdapter by lazy { ProjectListAdapter() }

    private val projectTree by lazy { arguments?.getSerializable(ParamsKeyConstant.TREE) as ProjectTree }

    companion object {

        fun getInstance(tree: ProjectTree): ProjectListFragment {
            val fragment = ProjectListFragment()
            fragment.arguments = Bundle().apply {
                putSerializable(ParamsKeyConstant.TREE, tree)
            }
            return fragment
        }
    }

    private val viewModel by viewModels<ProjectListViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ProjectListViewModel(
                    repository = ProjectRepository(datasource = ProjectDatasource()),
                    collectRepository = CollectRepository(dataSource = CollectDataSource())
                ) as T
            }
        }
    }


    override var layoutResId: Int = R.layout.fragment_project_list

    override fun onCreate() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {

            override fun onRefresh(refreshLayout: RefreshLayout) {

                adapter.isRefresh = true

                adapter.currPage = 0

                viewModel.getProjectList(id, projectTree.id)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.getProjectList(adapter.currPage, projectTree.id)
            }
        })

        recyclerView.run {

            layoutManager = LinearLayoutManager(requireContext())

            adapter = this@ProjectListFragment.adapter
        }


        adapter.setOnItemChildClickListener(object : OnItemChildClickListener {
            override fun onItemChildClick(
                adapter: BaseQuickAdapter<*, *>,
                view: View,
                position: Int
            ) {
                var project = adapter.data[position] as Article

                when (view.id) {
                    R.id.itemView -> {
                        val intent = Intent(requireActivity(), CommonWebActivity::class.java)
                        intent.putExtra(ParamsKeyConstant.ARTICLE, project)
                        ActivityUtils.getTopActivity().startActivity(intent)
                    }

                    R.id.iv_favorite -> {
                        //收藏
                        if (project.collect) {

                            (view as ImageView).setImageResource(R.drawable.ic_un_like)

                            viewModel.cancelCollect(project.id!!)

                        } else {
                            (view as ImageView).setImageResource(R.drawable.ic_like)
                            //收藏文章
                            viewModel.collect(project.id!!)
                            //执行动画
                            view.collectAnimation()
                        }

                        (adapter.data[position] as Article).collect = !project.collect
                    }
                }
            }
        })
    }

    override fun onLoadData() {

        viewModel.getProjectList(id = projectTree.id)

        viewModel.projectLiveData.observe(this) {
            if (adapter.isRefresh) {
                adapter.setNewInstance(it.datas)
                smartRefreshLayout.finishRefresh()
            } else {
                adapter.addData(it.datas!!)
                adapter.currPage++
                smartRefreshLayout.finishLoadMore()
            }
            adapter.isRefresh = false
        }
    }
}