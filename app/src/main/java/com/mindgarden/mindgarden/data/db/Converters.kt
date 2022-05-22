package com.mindgarden.mindgarden.data.db

import androidx.room.TypeConverter
import java.time.LocalDateTime

object Converters {
    @TypeConverter
    @JvmStatic
    fun toLocalDateTime(dateString: String?): LocalDateTime? {
        return if (dateString == null) {
            null
        } else {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    @JvmStatic
    fun toDateString(date: LocalDateTime?): String? {
        return date?.toString()
    }
}