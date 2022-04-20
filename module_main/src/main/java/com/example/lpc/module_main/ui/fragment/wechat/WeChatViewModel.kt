package com.example.lpc.module_main.ui.fragment.wechat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lpc.lib_common.base.viewmodel.BaseViewModel
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.pojo.WxChapter
import kotlinx.coroutines.launch

/**
 * Author: liupengchao
 * Date: 2022/4/20
 * ClassName :WeChatViewModel
 * Desc:
 */
class WeChatViewModel(private val repository: WeChatRepository) : BaseViewModel() {

    private val _chapterLiveData = MutableLiveData<MutableList<WxChapter>>()
    val chapterLiveData: LiveData<MutableList<WxChapter>> = _chapterLiveData

    private val _articleLiveData = MutableLiveData<PageVo<Article>>()
    val articleLiveData: LiveData<PageVo<Article>> = _articleLiveData


    fun getWxChapters() {

        viewModelScope.launch {

            var result = repository.getWxChapterList()

            if (result is Results.Success) {
                _chapterLiveData.postValue(result.data!!)
            }
        }
    }

    fun getWxArticle(page: Int = 0, id: String) {

        viewModelScope.launch {

            var result = repository.getWxArticle(page, id)

            if (result is Results.Success) {
                _articleLiveData.postValue(result.data!!)
            }
        }
    }
}