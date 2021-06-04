package com.mindgarden.mindgarden.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    @PrimaryKey
    @ColumnInfo(name="idx")
    val idx:Long,
    @ColumnInfo(name="content")
    val content:String,
    @ColumnInfo(name="weather")
    val weather:Int,
    @ColumnInfo(name="img")
    val img:String
)