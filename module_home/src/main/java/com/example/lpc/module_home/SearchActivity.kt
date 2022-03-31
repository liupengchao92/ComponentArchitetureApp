package com.example.lpc.module_home

import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.lib_common.constant.ParamsKeyConstant
import com.example.lpc.lib_common.http.pojo.HotKey
import com.example.lpc.module_home.databinding.ActivitySearchBinding
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.activity_search.*

/**
 * 搜索页面
 */
@Route(path = ARouterConstant.Home.SEARCH_PATH)
class SearchActivity : BaseBindingActivity<ActivitySearchBinding>() {

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

        flowLayout.adapter = hotKeyList?.let { HotKeyAdapter(it) }

        flowLayout.setOnTagClickListener(object :TagFlowLayout.OnTagClickListener{
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                ToastUtils.showShort(hotKeyList?.get(position)?.name)
                return true
            }
        })
        //搜索
        searchEdit.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ToastUtils.showShort("开始搜索");
                    return true;
                }
                return false
            }
        })

        clearIv.setOnClickListener {
            searchEdit.setText("")
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