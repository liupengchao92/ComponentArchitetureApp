package com.example.lpc.lib_common.eventbus

import android.util.Log
import androidx.lifecycle.*

/**
 * Author: liupengchao
 * Date: 2022/3/13
 * ClassName :ExternalLiveData
 * Desc:LiveData
 */
class ExternalLiveData<T>(val key: String) : MutableLiveData<T>() {

    private val mObservers = mutableMapOf<Observer<in T>, ExternalObserverWrapper<T>>()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val externalObserver = LifecycleExternalObserver(owner, observer, this).apply {
            mObservers[observer] = this
            owner.lifecycle.addObserver(this)
        }
        super.observe(owner, externalObserver)
    }

    override fun observeForever(observer: Observer<in T>) {

        val alwaysExternalObserver = AlwaysExternalObserver(observer, this);

        super.observeForever(alwaysExternalObserver)
    }

    override fun removeObserver(observer: Observer<in T>) {

        val exist = mObservers.remove(observer) ?: observer

        super.removeObserver(exist as Observer<in T>)
    }

    override fun removeObservers(owner: LifecycleOwner) {
        mObservers.iterator().forEach { item ->
            if (item.value.isAttachedTo(owner)) {
                mObservers.remove(item.key)
            }
        }
        super.removeObservers(owner)
    }

    override fun onInactive() {
        super.onInactive()
        if (!hasObservers()) {
            LiveEventBus.removeKey(key)
        }
    }


    fun observeSticky(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    fun observeStickyForever(observer: Observer<in T>) {
        super.observeForever(observer)
    }

    /**
     * 获取mVersion
     *
     * */
    fun getVersion(): Int {

        kotlin.runCatching {

            var clazz: Class<Any> = this.javaClass;

            while (clazz != null) {

                val fields = clazz.declaredFields

                if (fields.isNotEmpty()) {

                    fields.forEach {

                        if ("mVersion" == it.name) {

                            it.isAccessible = true

                            val mVersion = it.getInt(this);

                            return mVersion
                        }
                    }
                }

                clazz = clazz.superclass

            }
            return -1;
        }.onSuccess {
            return@onSuccess
        }.onFailure {
            Log.e("ceshi", "getVersion: ${it.message}")
        }
        return -1
    }

    /**
     * 绑定生命周期的观察者包装类
     * @param T
     * @property owner LifecycleOwner
     * @constructor
     */
    internal class LifecycleExternalObserver<T>(
        val owner: LifecycleOwner,
        val observer: Observer<in T>,
        val liveData: ExternalLiveData<T>
    ) : ExternalObserverWrapper<T>(observer, liveData), LifecycleEventObserver {


        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

            if (event == Lifecycle.Event.ON_DESTROY) {
                liveData.mObservers.remove(observer)
                source.lifecycle.removeObserver(this)
            }
        }

        override fun isAttachedTo(owner: LifecycleOwner): Boolean = this.owner == owner

    }

    /**
     * 观察者包装类
     * @param T
     */
    internal class AlwaysExternalObserver<T>(
        val observer: Observer<in T>,
        val liveData: ExternalLiveData<T>
    ) : ExternalObserverWrapper<T>(observer, liveData)

}