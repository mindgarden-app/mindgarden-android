package com.mindgarden.mindgarden.data.repository.common

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindgarden.mindgarden.data.model.local.Tree
import java.util.*

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    @JvmStatic
    fun jsonToMap(value: String): Map<Int, Tree> {
        return Gson().fromJson(value, object : TypeToken<Map<Int, Tree>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun mapToJson(value: Map<Int, Tree>?): String {
        return if (value == null) "" else Gson().toJson(value)
    }
}