package com.example.lpc.lib_common.base.activity

import android.os.Bundle
import android.view.MenuItem
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

    abstract fun  getViewBinding():VB

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}