package com.example.lpc.module_profile.ui.collect

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.utils.service.CollectService
import com.example.lpc.module_profile.R
import com.example.lpc.module_profile.databinding.ActivityCollectBinding

@Route(path = ARouterConstant.Profile.COLLECT_PATH)
class CollectActivity : BaseBindingActivity<ActivityCollectBinding>() {


    private val adapter: CollectAdapter = CollectAdapter()

    private val viewModel by viewModels<CollectViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CollectViewModel(repository = CollectListRepository(dataSource = CollectListDatasource())) as T
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.toolBar.run {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            setNavigationIcon(R.drawable.ic_back_white)
        }

        val collectService: CollectService =
            ARouter.getInstance().build(ARouterConstant.Service.SERVICE_COLLECT)
                .navigation() as CollectService

        val personInfo = collectService.getPersonInfo()

        LogUtils.d("personInfo : $personInfo")

        binding.recyclerView.run {

            layoutManager = LinearLayoutManager(this.context)

            adapter = this@CollectActivity.adapter

        }

        binding.refreshLayout.setOnLoadMoreListener {

            viewModel.getCollectList(adapter.currentPage)
        }

        viewModel.collectLiveData.observe(this) {

            adapter.addData(it.datas!!)

            adapter.currentPage = it.curPage

            binding.refreshLayout.finishLoadMore()
        }

        viewModel.getCollectList(0)
    }

    override fun getViewBinding(): ActivityCollectBinding {
        return ActivityCollectBinding.inflate(layoutInflater)
    }
}