package com.mindgarden.mindgarden.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mindgarden.mindgarden.presentation.diary.weather.Weather
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
    val weather: Weather,
    @ColumnInfo(name = "img")
    val img: List<String>?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var idx: Long = 0
}

data class DiaryUpdate(
    val idx: Long,
    val contents: String,
    val weather: Weather,
    val img: List<String>?
) {
    companion object {
        fun fromDiary(old: Diary) = DiaryUpdate(
            idx = old.idx,
            contents = old.contents,
            weather = old.weather,
            img = old.img
        )
    }
}