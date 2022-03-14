package com.example.lpc.lib_common.eventbus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * Author: liupengchao
 * Date: 2022/3/13
 * ClassName :ObserverWrapper
 * Desc:观察者包装类
 */
open class ExternalObserverWrapper<T>(
    private val observer: Observer<in T>,
    private val liveData: ExternalLiveData<T>
) :
    Observer<T> {

    private val version = liveData.getVersion()

    override fun onChanged(t: T) {

        if (version >= liveData.getVersion()) {
            return
        }

        observer.onChanged(t)
    }

    open fun isAttachedTo(owner: LifecycleOwner): Boolean = false
}