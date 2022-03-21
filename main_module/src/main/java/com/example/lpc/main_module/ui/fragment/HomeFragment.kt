package com.example.lpc.main_module.ui.fragment

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.http.BaseVo
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper
import com.example.lpc.main_module.R
import com.example.lpc.main_module.ui.adapter.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/3/18
 * ClassName :HomeFragment
 * Desc:主页
 */
class HomeFragment : BaseFragment() {

    //适配器
    private var adapter: ArticleAdapter = ArticleAdapter(mutableListOf())


    override var layoutResId: Int = R.layout.fragment_home

    override fun onCreate() {

    }

    override fun onLoadData() {

        LogUtils.e("HomeFragment=====>>onCreate")


        recyclerView.run {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@HomeFragment.adapter;
            itemAnimator = DefaultItemAnimator()
        }



        GlobalScope.launch {
            var response = RetrofitHelper.apiService.getHomeArticle(1)
            kotlin.runCatching {
                if (response.isSuccessful) {
                    var body: BaseVo<PageVo<Article>>? = response.body()
                    requireActivity().runOnUiThread{
                        adapter.setNewInstance(body?.data?.datas)

                    }
                }
            }.onFailure {
                LogUtils.e(it.toString())

            }
        }
    }
}