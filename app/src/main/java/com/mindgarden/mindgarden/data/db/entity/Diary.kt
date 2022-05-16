package com.mindgarden.mindgarden.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Diary(
    @ColumnInfo(name = "date")
    val date: LocalDateTime,
    @ColumnInfo(name = "contents")
    val contents: String,
    @ColumnInfo(name = "weather")
    val weather: Int,
    @ColumnInfo(name = "weatherText")
    val weatherText: String,
    @ColumnInfo(name = "img")
    val img: String?
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Long = 0
}

data class DiaryUpdate(
    val idx: Long,
    val contents: String,
    val weather: Int,
    val weatherText: String,
    val img: String?
)