package com.mindgarden.mindgarden.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Diary(
    @PrimaryKey
    @ColumnInfo(name = "idx")
    val idx: Long,
    @ColumnInfo(name = "date")
    val date: Date,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "weather")
    val weather: Int,
    @ColumnInfo(name = "weatherTxt")
    val weatherTxt: Int,
    @ColumnInfo(name = "img")
    val img: String
)