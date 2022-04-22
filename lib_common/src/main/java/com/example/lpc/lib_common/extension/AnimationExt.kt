package com.example.lpc.lib_common.extension

import android.animation.ValueAnimator
import android.view.View

/**
 * Author: liupengchao
 * Date: 2022/4/22
 * ClassName :AnimationExt
 * Desc:动画
 */


inline fun View.collectAnimation() {
    ValueAnimator.ofFloat(1f, 2f, 1f).apply {
        addUpdateListener { animation ->
            scaleX = animation?.animatedValue as Float
            scaleY = animation?.animatedValue as Float
        }
        duration = 300
        start()
    }

}