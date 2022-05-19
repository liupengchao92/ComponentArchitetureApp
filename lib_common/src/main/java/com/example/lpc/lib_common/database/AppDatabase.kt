package com.example.lpc.lib_common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lpc.lib_common.database.dao.KeywordDao
import com.example.lpc.lib_common.database.entity.KeyWord

/**
 * Author: liupengchao
 * Date: 2022/4/8
 * ClassName :AppDatabase
 * Desc:
 */
@Database(entities = [KeyWord::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getKeywordDao(): KeywordDao

}