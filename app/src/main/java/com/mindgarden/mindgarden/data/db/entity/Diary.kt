package com.mindgarden.mindgarden.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
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
    val img: List<String>?
) : Parcelable {
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