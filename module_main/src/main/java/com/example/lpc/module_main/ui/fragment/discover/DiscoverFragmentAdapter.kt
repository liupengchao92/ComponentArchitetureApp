package com.example.lpc.module_main.ui.fragment.discover

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lpc.module_main.ui.fragment.project.HotProjectFragment
import com.example.lpc.module_main.ui.fragment.question.QuestionFragment
import com.example.lpc.module_main.ui.fragment.square.SquareFragment
import com.example.lpc.module_main.ui.fragment.wechat.WeChatAccountFragment

/**
 * Author: liupengchao
 * Date: 2022/4/18
 * ClassName :Pager2Adapter
 * Desc:
 */
class DiscoverFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = mutableListOf(SquareFragment(),QuestionFragment(), WeChatAccountFragment())

    override fun getItemCount(): Int = fragments.size


    override fun createFragment(position: Int): Fragment = fragments[position]
}