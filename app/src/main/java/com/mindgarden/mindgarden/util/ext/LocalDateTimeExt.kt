package com.mindgarden.mindgarden.util.ext

import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun now(): LocalDateTime = LocalDateTime.now()

fun LocalDateTime.toGardenDate(): LocalDateTime = LocalDateTime.of(
    this.year, this.month, 1, 0, 0, 0, 0
)

fun LocalDateTime.toStringOfPattern(pattern: String): String {
    Log.d("toGardenDateString", "$this")
    return this.format(DateTimeFormatter.ofPattern(pattern))
}