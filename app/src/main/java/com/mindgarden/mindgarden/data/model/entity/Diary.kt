package com.mindgarden.mindgarden.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Diary(
    @ColumnInfo(name = "date")
    val date: Date,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "weather")
    val weather: Int,
    @ColumnInfo(name = "weatherTxt")
    val weatherTxt: String,
    @ColumnInfo(name = "img")
    val img: String?
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idx")
    var idx: Long = 0
}