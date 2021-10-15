package com.mindgarden.mindgarden.util.ext

import java.time.LocalDateTime

fun now(): LocalDateTime = LocalDateTime.now()

fun LocalDateTime.getGardenDate(): LocalDateTime =  LocalDateTime.of(
    this.year, this.month, 1, 0, 0,0,0)