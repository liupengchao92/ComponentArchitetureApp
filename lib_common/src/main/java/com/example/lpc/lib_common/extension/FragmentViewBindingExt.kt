package com.example.lpc.lib_common.extension

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Author: liupengchao
 * Date: 2022/4/13
 * ClassName :FragmentViewBindingExt
 * Desc:
 */


fun <VB:ViewBinding>Fragment.binding(bind:(View) -> VB) = FragmentBindingDelegate(bind)


class FragmentBindingDelegate <VB :ViewBinding> (private val bind: (View) -> VB): ReadOnlyProperty<Fragment,VB>{

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {

        requireNotNull(thisRef.view) { "The property of ${property.name} has been destroyed." }.let { view ->

            return view.getTag(Int.MAX_VALUE) as? VB?:bind(view).also { view.setTag(Int.MAX_VALUE,it) }
        }
    }
}