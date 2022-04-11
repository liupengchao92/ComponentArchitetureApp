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

    @Query("SELECT * FROM KEYWORD ORDER BY id desc")
    fun getAllKeyWord(): MutableList<KeyWord>

    @Query("SELECT * FROM KEYWORD WHERE key_word = :keyWord")
    fun queryByKeyWord(keyWord: String): MutableList<KeyWord>

    @Query("SELECT COUNT('key_word') FROM KEYWORD WHERE key_word= :keyWord")
    fun queryByKeyWordCount(keyWord: String):Int

    @Insert
    fun insertAll(vararg keyWords: KeyWord)

    @Delete
    fun delete(keyWord: KeyWord)
}