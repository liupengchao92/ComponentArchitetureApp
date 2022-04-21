package com.example.lpc.module_main.ui.fragment.project

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.http.pojo.ProjectTree
import com.example.lpc.module_main.R
import com.example.lpc.module_main.ui.fragment.home.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_project_list.*

/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ProjectListFragment
 * Desc:
 */
class ProjectListFragment : BaseFragment() {

    private val adapter: ArticleAdapter by lazy { ArticleAdapter() }

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
                return ProjectListViewModel(repository = ProjectRepository(datasource = ProjectDatasource())) as T
            }
        }
    }


    override var layoutResId: Int = R.layout.fragment_project_list


    override fun onCreate() {


        recyclerView.run {

            layoutManager = LinearLayoutManager(requireContext())

            adapter = this@ProjectListFragment.adapter
        }
    }

    override fun onLoadData() {

        viewModel.getProjectList(id = projectTree.id)

        viewModel.projectLiveData.observe(this) {

            adapter.setNewInstance(it.datas)

        }
    }
}