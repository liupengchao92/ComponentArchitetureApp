package com.example.lpc.module_main.ui.fragment.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.ProjectTree
import com.example.lpc.module_main.R
import com.example.lpc.module_main.ui.activity.ui.web.CommonWebActivity
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
                       ToastUtils.showShort("收藏")
                    }
                }
            }
        })
    }

    override fun onLoadData() {

        viewModel.getProjectList(id = projectTree.id)

        viewModel.projectLiveData.observe(this) {

            adapter.setNewInstance(it.datas)

        }
    }
}