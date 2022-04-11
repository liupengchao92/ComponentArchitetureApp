package com.example.lpc.lib_common.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: liupengchao
 * Date: 2022/4/8
 * ClassName :KeyWord
 * Desc:
 */
@Entity(tableName = "keyword")
data class KeyWord(

    //主键
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "key_word")
    val keyWord: String

)