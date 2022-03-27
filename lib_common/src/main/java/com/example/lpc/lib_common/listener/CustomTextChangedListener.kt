package com.example.lpc.lib_common.listener

import android.text.Editable
import android.text.TextWatcher

/**
 * Author: liupengchao
 * Date: 2022/3/25
 * ClassName :CustomTextChangedListener
 * Desc:
 */
abstract class CustomTextChangedListener : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        onTextChanged(s.toString())
    }

    abstract fun onTextChanged(text: String?)
}