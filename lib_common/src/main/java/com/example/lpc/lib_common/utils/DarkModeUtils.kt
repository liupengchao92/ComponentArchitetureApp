package com.example.lpc.lib_common.utils

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

/**
 * Author: liupengchao
 * Date: 2022/6/10
 * ClassName :DarkModeUtils
 * Desc:
 */
object DarkModeUtils {

    private const val KEY_CURRENT_MODEL = "night_mode_state_sp"

    /**
     * 初始化
     */
    fun init() {

        val nightModel = getNightModel()

        AppCompatDelegate.setDefaultNightMode(nightModel)

    }

    /**
     * 获取夜间模式
     * @param context Context
     * @return Int
     */
    fun getNightModel(): Int {

        return MMKVUtils.getInt(KEY_CURRENT_MODEL, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    /**
     * 存储夜间模式
     * @param nightMode Int
     */
    fun setNightModel(nightMode: Int) {
        MMKVUtils.putValue(KEY_CURRENT_MODEL, nightMode)
    }

    /**
     * 应用日间模式
     */
    fun applyDayMode() {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setNightModel(AppCompatDelegate.MODE_NIGHT_NO);
    }

    /**
     *
     * 应用夜间模式
     *
     */
    fun applyNightMode() {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        setNightModel(AppCompatDelegate.MODE_NIGHT_YES)
    }

    /**
     *
     * 跟随系统主题时需要动态切换
     *
     */
    fun applySystemMode() {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        setNightModel(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    /**
     * 是否跟随系统
     * @param context Context
     * @return Boolean
     */
    fun isFollowSystemDarkMode(): Boolean {
        val nightMode = getNightModel()
        return nightMode == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    /**
     *
     * 判断App当前是否处于暗黑模式状态
     *
     *
     *
     * @param context 上下文
     *
     * @return 返回
     */
    fun isDarkMode(context: Context): Boolean {
        val nightMode = getNightModel()
        return if (nightMode == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) {
            val applicationUiMode: Int = context.resources.configuration.uiMode
            val systemMode = applicationUiMode and Configuration.UI_MODE_NIGHT_MASK
            systemMode == Configuration.UI_MODE_NIGHT_YES
        } else {
            nightMode == AppCompatDelegate.MODE_NIGHT_YES
        }
    }
}