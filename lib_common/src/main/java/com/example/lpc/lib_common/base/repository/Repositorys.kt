package com.example.lpc.lib_common.base.repository

/**
 * Author: liupengchao
 * Date: 2022/3/18
 * ClassName :Repositorys
 * Desc:
 */


open class BaseRepositoryBoth<R : IRemoteDataSource, L : ILocalDataSource>(var remoteDataSource: R, var localDataSource: L) : IRepository


open class BaseRepositoryRemote<R : IRemoteDataSource>(var remoteDataSource: R) : IRepository


open class BaseRepositoryLocal<L : ILocalDataSource>(var localDataSource: L):IRepository


open class BaseRepositoryNothing() : IRepository

