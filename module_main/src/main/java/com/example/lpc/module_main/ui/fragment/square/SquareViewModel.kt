package com.example.lpc.module_main.ui.fragment.square

import com.example.lpc.module_main.ui.activity.ui.collect.CollectRepository
import com.example.lpc.module_main.ui.activity.ui.collect.CollectViewModel

/**
 * Author: liupengchao
 * Date: 2022/4/19
 * ClassName :SquareViewModel
 * Desc:
 */
class SquareViewModel(
    private val repository: SquareRepository,
    private val collectRepository: CollectRepository
) : CollectViewModel(collectRepository) {

    val flow = repository.getSquareListByPaging()
}