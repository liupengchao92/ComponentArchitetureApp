package com.example.lpc.lib_common.extension

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

/**
 * Author: liupengchao
 * Date: 2022/4/13
 * ClassName :ViewBindingExt
 * Desc:
 */


fun <VB : ViewBinding> Activity.inflate(inflater: (LayoutInflater) -> VB) = lazy {

    inflater(layoutInflater).apply {
        setContentView(this.root)
    }
}

inline fun <reified VB : ViewBinding> Activity.inflate() = lazy {

    inflateBinding<VB>(layoutInflater).apply {

        setContentView(this.root)
    }
}


inline fun <reified VB> inflateBinding(layoutInflater: LayoutInflater): VB {

    return VB::class.java.getMethod("inflate ", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB

}