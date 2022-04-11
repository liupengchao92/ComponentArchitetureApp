package com.example.lpc.module_home.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.database.entity.KeyWord
import com.example.lpc.lib_common.http.pojo.HotKey
import com.example.lpc.module_home.R
import com.example.lpc.module_home.databinding.ActivitySearchBinding
import com.example.lpc.module_home.databinding.ItemSearchTagBinding
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.activity_search.*

/**
 * 搜索页面
 */
@Route(path = ARouterConstant.Home.SEARCH_PATH)
class SearchActivity : BaseBindingActivity<ActivitySearchBinding>() {

    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory() }

    private val articleAdapter = ArticleAdapter(mutableListOf())

    private val historyAdapter = SearchHistoryAdapter(mutableListOf())

    @JvmField
    @Autowired(name = ParamsKeyConstant.CURRENT_HOT_KEY)
    var hotKey: String = ""

    @JvmField
    @Autowired(name = ParamsKeyConstant.HOT_KEY_LIST)
    var hotKeyList: ArrayList<HotKey>? = null

    override fun getViewBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)

        searchEdit.setText(hotKey)

        viewModel.getAllKeyword()

        viewModel.resultData.observe(this) {
            contentLayout.visibility = View.VISIBLE
            if (it.datas.isNullOrEmpty()) {
                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                articleAdapter.setNewInstance(it.datas)
            }
        }

        viewModel.keywordData.observe(this) {
            historyAdapter.setNewInstance(it)
            historyAdapter.notifyDataSetChanged()
        }

        recyclerView.run {

            layoutManager = LinearLayoutManager(context)

            itemAnimator = DefaultItemAnimator()

            adapter = articleAdapter
        }

        historyRv.run {

            layoutManager = LinearLayoutManager(context)

            itemAnimator = DefaultItemAnimator()

            adapter = historyAdapter
        }

        var tagBinding = ItemSearchTagBinding.inflate(layoutInflater, null, false)

        tagBinding.flowLayout.adapter = hotKeyList?.let { HotKeyAdapter(it) }

        tagBinding.flowLayout.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener {
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                viewModel.search(0, hotKeyList?.get(position)?.name!!)
                return true
            }
        })

        historyAdapter.addHeaderView(tagBinding.root)
        //点击事件
        historyAdapter.setOnItemChildClickListener(object : OnItemChildClickListener {
            override fun onItemChildClick(
                adapter: BaseQuickAdapter<*, *>,
                view: View,
                position: Int
            ) {
                var keyWord = adapter.data[position] as KeyWord
                adapter.removeAt(position)
                viewModel.delete(keyWord)
            }
        })

        //搜索
        searchEdit.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.search(0, searchEdit.text.toString())
                    return true;
                }
                return false
            }
        })

        searchEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                contentLayout.visibility = View.GONE
            }
        })

        clearIv.setOnClickListener {
            searchEdit.setText("")
            contentLayout.visibility = View.GONE
        }
        //关掉当前页面
        cancelTv.setOnClickListener {
            finish()
        }
    }

    inner class HotKeyAdapter(tags: ArrayList<HotKey>) : TagAdapter<HotKey>(tags) {
        override fun getView(parent: FlowLayout?, position: Int, t: HotKey?): View {

            return TextView(parent?.context).apply {

                text = t?.name

                textSize = 14f

                gravity = Gravity.CENTER

                setPadding(20, 8, 20, 8)

                setTextColor(ContextCompat.getColor(this.context, R.color.colorPrimary))

                setBackgroundResource(R.drawable.shape_search_tag_bg)
            }
        }
    }
}