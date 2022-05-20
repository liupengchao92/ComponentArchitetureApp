package com.example.lpc.module_profile.ui.setting

import android.os.Bundle
import android.view.View
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.lpc.lib_common.base.activity.BaseBindingActivity
import com.example.lpc.lib_common.constant.ARouterConstant
import com.example.lpc.module_profile.R
import com.example.lpc.module_profile.databinding.ActivitySettingBinding
import kotlinx.android.synthetic.main.activity_setting.*


@Route(path = ARouterConstant.Profile.SETTING_PATH)
class SettingActivity : BaseBindingActivity<ActivitySettingBinding>() {

    override fun getViewBinding(): ActivitySettingBinding {
        return ActivitySettingBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarColor(this, ColorUtils.getColor(R.color.white))

        toolBar.run {
            title = ""
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setNavigationIcon(R.drawable.ic_back_black)
        }

        binding.darkSwitchButton.setOnCheckedChangeListener { buttonView, isChecked -> ToastUtils.showShort(" 是否选中${isChecked}") }

        binding.accountLayout.setOnClickListener(onClickListener)
        binding.clearCacheLayout.setOnClickListener(onClickListener)
        binding.feedbackLayout.setOnClickListener(onClickListener)
        binding.checkVersionLayout.setOnClickListener(onClickListener)
        binding.aboutLayout.setOnClickListener(onClickListener)
    }

    private var onClickListener = View.OnClickListener {

                when(it.id){

                    R.id.accountLayout ->{
                        ToastUtils.showShort(R.string.accounts_security)
                    }
                    R.id.clearCacheLayout ->{
                        ToastUtils.showShort(R.string.clear_cache)
                    }
                    R.id.feedbackLayout ->{
                        ToastUtils.showShort(R.string.feedback)
                    }
                    R.id.checkVersionLayout ->{
                        ToastUtils.showShort(R.string.check_version)
                    }
                    R.id.aboutLayout ->{
                        ToastUtils.showShort(R.string.about_android)
                    }
                }
         }

}