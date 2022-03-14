package com.example.lpc.lib_common.eventbus

import androidx.lifecycle.LiveData

/**
 * Author: liupengchao
 * Date: 2022/3/13
 * ClassName :LiveEventBus
 * Desc:基于LiveData实现事件总线
 */
object LiveEventBus {

    private val eventBusMap = HashMap<String, LiveData<Any>>()

    fun <T> with(key: String, clazz: Class<T>? = null): ExternalLiveData<T> {

        if (!eventBusMap.containsKey(key)) {
            eventBusMap[key] = ExternalLiveData(key)
        }
        return eventBusMap[key] as ExternalLiveData<T>
    }

    fun <T> with(key: String): ExternalLiveData<T> = with<T>(key, null)


    fun removeKey(key: String) {
        eventBusMap.remove(key)
    }

}