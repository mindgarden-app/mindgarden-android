package com.mindgarden.mindgarden.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    @PrimaryKey
    @ColumnInfo(name="idx")
    val idx:Long,
    @ColumnInfo(name="date")
    val date:String,
    @ColumnInfo(name="content")
    val content:String,
    @ColumnInfo(name="weather")
    val weather:Int,
    @ColumnInfo(name="img")
    val img:String
)