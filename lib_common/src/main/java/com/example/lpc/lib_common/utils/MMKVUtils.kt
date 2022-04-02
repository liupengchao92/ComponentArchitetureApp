package com.example.lpc.lib_common.utils

import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 * Author: liupengchao
 * Date: 2022/3/29
 * ClassName :MMKVUtils
 * Desc:
 */
object MMKVUtils {

    private val mmkv = MMKV.defaultMMKV()

    /**
     * 存入数据
     * @param key String
     * @param value Any
     */
    fun putValue(key: String, value: Any) {

        when (value) {

            is String -> {

                mmkv.encode(key, value)
            }
            is Int -> {

                mmkv.encode(key, value)
            }
            is Long -> {

                mmkv.encode(key, value)
            }
            is Double -> {

                mmkv.encode(key, value)
            }
            is Float -> {

                mmkv.encode(key, value)
            }
            is Boolean -> {

                mmkv.encode(key, value)
            }

            is ByteArray -> {

                mmkv.encode(key, value)
            }

            is Parcelable -> {

                mmkv.encode(key, value)
            }

            is Set<*> -> {

                mmkv.encode(key, value as Set<String>)
            }
        }
    }


    fun getString(key: String, default: String = ""): String {
        return mmkv.decodeString(key, default)!!
    }

    fun getInt(key: String, default: Int = 0): Int {
        return mmkv.decodeInt(key, default)
    }

    fun getLong(key: String, default: Long = 0L): Long {
        return mmkv.decodeLong(key, default)
    }

    fun getDouble(key: String, default: Double = 0.0): Double {
        return mmkv.decodeDouble(key, default)
    }

    fun getFloat(key: String, default: Float = 0.0f): Float {
        return mmkv.decodeFloat(key, default)
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return mmkv.decodeBool(key, default)
    }

    fun getByte(key: String, default: ByteArray = byteArrayOf()): ByteArray {
        return mmkv.decodeBytes(key, default)!!
    }

    fun getSetString(key: String,default: Set<String> = mutableSetOf()): MutableSet<String>? {
        return mmkv.decodeStringSet(key,default)
    }

    fun remove(key: String) {
        mmkv.removeValueForKey(key)
    }

}

