package com.example.lpc.module_main.ui.fragment

import com.example.lpc.lib_common.base.fragment.BaseFragment
import com.example.lpc.lib_common.extension.binding
import com.example.lpc.module_main.R
import com.example.lpc.module_main.databinding.FragmentQuestionBinding

/**
 * Author: liupengchao
 * Date: 2022/3/18
 * ClassName :QuestionFragment
 * Desc:问答
 */
class QuestionFragment : BaseFragment() {

    private val binding by binding(FragmentQuestionBinding::bind)

    override var layoutResId: Int = R.layout.fragment_question

    override fun onCreate() {

    }

    override fun onLoadData() {

    }
}