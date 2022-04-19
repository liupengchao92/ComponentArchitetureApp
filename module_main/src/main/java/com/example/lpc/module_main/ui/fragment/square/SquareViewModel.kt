package com.example.lpc.module_main.ui.fragment.square

import com.example.lpc.lib_common.base.viewmodel.BaseViewModel

/**
 * Author: liupengchao
 * Date: 2022/4/19
 * ClassName :SquareViewModel
 * Desc:
 */
class SquareViewModel(private val repository: SquareRepository) : BaseViewModel() {

    val flow = repository.getSquareListByPaging()
}