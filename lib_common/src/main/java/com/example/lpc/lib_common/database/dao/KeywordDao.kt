package com.example.lpc.lib_common.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lpc.lib_common.database.entity.KeyWord

/**
 * Author: liupengchao
 * Date: 2022/4/8
 * ClassName :KeywordDao
 * Desc:
 */
@Dao
interface KeywordDao {

    @Query("SELECT * FROM KEYWORD ")
    fun getAllKeyWord(): MutableList<KeyWord>

    @Insert
    fun insertAll(vararg keyWords: KeyWord)

    @Delete
    fun delete(keyWord: KeyWord)
}