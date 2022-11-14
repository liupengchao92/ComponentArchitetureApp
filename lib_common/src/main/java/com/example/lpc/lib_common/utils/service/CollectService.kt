package com.example.lpc.lib_common.utils.service

import com.alibaba.android.arouter.facade.template.IProvider
import com.example.lpc.lib_common.http.pojo.PersonalInfo

/**
 * Author: liupengchao
 * Date: 2022/6/13
 * ClassName :CollectService
 * Desc:
 */
interface CollectService : IProvider {

    fun getPersonInfo(): PersonalInfo?
}