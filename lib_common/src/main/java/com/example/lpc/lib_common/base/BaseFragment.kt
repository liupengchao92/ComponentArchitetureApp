package com.example.lpc.lib_common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.properties.Delegates

/**
 * Author: liupengchao
 * Date: 2022/3/15
 * ClassName :BaseFragment
 * Desc:Fragment基础类
 */
abstract class BaseFragment() : Fragment() {

    lateinit var rootView: View

    var layoutResId by Delegates.notNull<Int>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(layoutResId, container, true)
        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onCreate()
        onLoadData()
    }

    abstract fun onCreate()

    abstract fun onLoadData()


}