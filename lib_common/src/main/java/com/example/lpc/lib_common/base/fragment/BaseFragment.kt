package com.example.lpc.lib_common.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.LogUtils
import kotlin.properties.Delegates

/**
 * Author: liupengchao
 * Date: 2022/3/15
 * ClassName :BaseFragment
 * Desc:Fragment基础类
 */
abstract class BaseFragment() : Fragment() {

    lateinit var rootView: View

    open var layoutResId by Delegates.notNull<Int>()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        //onActivityCreated 遭废弃,使用LifeCycle回调Activity的onCreate()方法
        requireActivity().lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_CREATE) {


                    //移除观察者
                    lifecycle.removeObserver(this)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(layoutResId, container, false)

        LogUtils.e("onCreateView=====>>")
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtils.e("onActivityCreated=====>>")

        onCreate()

        onLoadData()

    }

    abstract fun onCreate()

    abstract fun onLoadData()


}