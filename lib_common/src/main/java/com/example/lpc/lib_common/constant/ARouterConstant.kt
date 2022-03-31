package com.example.lpc.lib_common.constant

/**
 * Author: liupengchao
 * Date: 2022/3/15
 * ClassName :ARouterConstant
 * Desc:路由表
 */
object ARouterConstant {

    object App {

        private const val APP = "/app"

        const val SPLASH_PATH = "$APP/splash"

    }

    object Main {

        private const val MAIN = "/main"

        const val INDEX_PATH = "${MAIN}/index"

        const val SECOND_PATH = "${MAIN}/second"

        const val LOGIN_PATH = "${MAIN}/login"
    }

    object Home {

        private const val HOME = "/home"

        const val SEARCH_PATH = "${HOME}/search"
    }
}