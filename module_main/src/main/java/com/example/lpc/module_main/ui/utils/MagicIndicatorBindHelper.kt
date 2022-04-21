package com.example.lpc.module_main.ui.utils

import androidx.viewpager2.widget.ViewPager2
import net.lucode.hackware.magicindicator.MagicIndicator

/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ViewPagerHelper
 * Desc:
 */
object MagicIndicatorBindHelper {


    fun bind(magicIndicator: MagicIndicator, viewPager2: ViewPager2) {

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)

            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                magicIndicator.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                magicIndicator.onPageScrollStateChanged(state)
            }
        })
    }
}