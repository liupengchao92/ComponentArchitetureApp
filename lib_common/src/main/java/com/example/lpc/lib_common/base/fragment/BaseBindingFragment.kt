package com.example.lpc.lib_common.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Author: liupengchao
 * Date: 2022/3/17
 * ClassName :BaseBindingFragment
 * Desc:
 */
abstract class BaseBindingFragment<VB : ViewBinding> : Fragment() {

    private  var _binding:VB? = null

    protected val binding  get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = getViewBinding(layoutInflater,container)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onCreate()

        loadData()
    }

    abstract  fun onCreate()


    abstract fun  loadData()


    abstract fun getViewBinding(inflater: LayoutInflater,container: ViewGroup?):VB
}