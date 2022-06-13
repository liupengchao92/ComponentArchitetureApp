package com.example.lpc.lib_common.constant

/**
 * Author: liupengchao
 * Date: 2022/3/15
 * ClassName :ARouterConstant
 * Desc:路由表
 */
object ARouterConstant {

    object Service{

        private const val SERVICE = "/service"

        const val SERVICE_COLLECT = "$SERVICE/collect"

    }


    object Common {

        private const val COMMON = "/common"

        const val WEB = "$COMMON/web"

    }

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

    object Profile {

        private const val PROFILE = "/profile"

        const val SETTING_PATH = "${PROFILE}/setting"

        const val COLLECT_PATH = "${PROFILE}/collect"
    }
}