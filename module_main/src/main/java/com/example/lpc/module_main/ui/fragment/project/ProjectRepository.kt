package com.example.lpc.module_main.ui.fragment.project

import com.example.lpc.lib_common.base.repository.BaseRepositoryRemote
import com.example.lpc.lib_common.base.repository.IRemoteDataSource
import com.example.lpc.lib_common.extension.processApiResponse
import com.example.lpc.lib_common.http.Results
import com.example.lpc.lib_common.http.pojo.Article
import com.example.lpc.lib_common.http.pojo.PageVo
import com.example.lpc.lib_common.http.pojo.ProjectTree
import com.example.lpc.lib_common.http.retrofit.RetrofitHelper

/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ProjectRepository
 * Desc:
 */
class ProjectRepository(private val datasource: ProjectDatasource):BaseRepositoryRemote<ProjectDatasource>(datasource) {

    suspend fun getProjectTree(): Results<MutableList<ProjectTree>> {

        return  datasource.getProjectTree()
    }

    suspend fun getProjectList(page: Int, id: String): Results<PageVo<Article>> {

        return datasource.getProjectList(page, id)
    }
}


class ProjectDatasource : IRemoteDataSource {

    suspend fun getProjectTree(): Results<MutableList<ProjectTree>> {

        return processApiResponse { RetrofitHelper.apiService.getProjectTree() }
    }

    suspend fun getProjectList(page: Int, id: String): Results<PageVo<Article>> {

        return processApiResponse { RetrofitHelper.apiService.getProjectList(page, id) }
    }

}