package com.example.lpc.lib_common.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

/**
 * Author: liupengchao
 * Date: 2022/3/15
 * ClassName :BaseActivity
 * Desc:Activity 基础类
 */
abstract class BaseActivity() : AppCompatActivity() {

    open var layoutResId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}