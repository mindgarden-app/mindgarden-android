package com.mindgarden.mindgarden.data.repository.common

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.util.*


object Converters {

    @TypeConverter
    @JvmStatic
    fun fromTimestampToDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    @JvmStatic
    fun toDate(dateString: String?): LocalDateTime? {
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