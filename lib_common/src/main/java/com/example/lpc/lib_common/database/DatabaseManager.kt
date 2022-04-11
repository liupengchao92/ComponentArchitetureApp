package com.example.lpc.lib_common.database

import androidx.room.Room
import com.example.lpc.lib_common.application.BaseApplication
import com.example.lpc.lib_common.database.entity.KeyWord

/**
 * Author: liupengchao
 * Date: 2022/4/8
 * ClassName :DatabaseManager
 * Desc:
 */
class DatabaseManager private constructor() {

    companion object {

        val INSTANCE = DatabaseManager()

    }

    private val database: AppDatabase by lazy {
        Room.databaseBuilder(
            BaseApplication.INSTANCE.applicationContext,
            AppDatabase::class.java,
            "search"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    fun getAll(): MutableList<KeyWord> {
        return database.getKeywordDao().getAllKeyWord()
    }

    fun queryByKeyword(keyWord: String): MutableList<KeyWord> {
        return database.getKeywordDao().queryByKeyWord(keyWord)
    }

    fun insertAll(keyWord: KeyWord) {
        database.getKeywordDao().insertAll(keyWord)
    }

    fun delete(keyWord: KeyWord) {
        database.getKeywordDao().delete(keyWord)
    }
}