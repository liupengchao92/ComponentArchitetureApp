package com.example.lpc.lib_common.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Author: liupengchao
 * Date: 2022/3/17
 * ClassName :BaseBindingActivity
 * Desc:
 */
abstract class BaseBindingActivity<VB : ViewBinding> :AppCompatActivity(){

    private  var _binding:VB? = null

    val binding:VB get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= getViewBinding().apply {
            setContentView(root)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun  getViewBinding():VB
}