package com.mindgarden.mindgarden.presentation.diary.weather

import android.os.Parcelable
import com.mindgarden.mindgarden.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val weatherType: WeatherType,
    var customText: String = ""
) : Parcelable

enum class WeatherType(val img: Int, val defaultText: String) {
    Sunny(R.drawable.img_weather_1_sunny, "좋아요"),
    Snowy(R.drawable.img_weather_2_snowy, "신나요"),
    Foggy(R.drawable.img_weather_3_foggy, "그냥 그래요"),
    Windy(R.drawable.img_weather_4_windy, "심심해요"),
    Hurricane(R.drawable.img_weather_5_hurricane, "재미있어요"),
    Rainbow(R.drawable.img_weather_6_rainbow, "설레요"),
    Cloudy(R.drawable.img_weather_7_cloudy, "별로예요"),
    Rainy(R.drawable.img_weather_8_rainy, "우울해요"),
    CloudWithThunder(R.drawable.img_weather_9_cloudwiththunder, "짜증나요"),
    Thunderbolt(R.drawable.img_weather_10_thunderbolt, "화가나요"),
    Default(R.drawable.img_weather_11_logo_large, "기분 없음");
}