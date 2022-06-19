package com.mindgarden.mindgarden.data.db

import androidx.room.TypeConverter
import com.mindgarden.mindgarden.presentation.diary.weather.Weather
import com.squareup.moshi.JsonAdapter
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
    fun jsonToWeather(json: String): Weather? {
        val adapter: JsonAdapter<Weather> = moshi.adapter(Weather::class.java)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun weatherToJson(weather: Weather): String {
        val adapter: JsonAdapter<Weather> = moshi.adapter(Weather::class.java)
        return adapter.toJson(weather)
    }

    @TypeConverter
    fun jsonToStringList(string: String): List<String> {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val typeAdapter = moshi.adapter<List<String>>(type)
        return typeAdapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun stringListToJson(list: List<String>): String {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val typeAdapter = moshi.adapter<List<String>>(type)
        return typeAdapter.toJson(list)
    }
}