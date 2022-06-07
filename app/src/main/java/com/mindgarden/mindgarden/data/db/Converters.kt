package com.mindgarden.mindgarden.data.db

import android.net.Uri
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
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

    private lateinit var moshi: Moshi

    fun initialize(moshi: Moshi) {
        this.moshi = moshi
    }

    @TypeConverter
    fun stringToMembers(string: String): List<String> {
        val membersType = Types.newParameterizedType(List::class.java, String::class.java)
        val membersAdapter = moshi.adapter<List<String>>(membersType)
        return membersAdapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun membersToString(members: List<String>): String {
        val membersType = Types.newParameterizedType(List::class.java, String::class.java)
        val membersAdapter = moshi.adapter<List<String>>(membersType)
        return membersAdapter.toJson(members)
    }
}