package com.mindgarden.mindgarden

import com.mindgarden.mindgarden.data.db.entity.Diary
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month


val mockLocalTime1: LocalTime = LocalTime.of(3, 0, 0)
val mockLocalTime2: LocalTime = LocalTime.of(12, 20, 0)
val mockLocalDate1: LocalDate = LocalDate.of(2022, Month.APRIL, 4)
val mockLocalDate2: LocalDate = LocalDate.of(2022, Month.APRIL, 5)
val mockLocalDate3: LocalDate = LocalDate.of(2022, Month.MAY, 6)
val mockDefaultDiary = Diary(
    LocalDateTime.of(mockLocalDate1, mockLocalTime1),
    "2022-04-04 0:0:0",
    0,
    "sunny",
    null
)

val mockDiaryList = listOf<Diary>(
    mockDefaultDiary,
    mockDefaultDiary.copy(
        date = LocalDateTime.of(mockLocalDate1, mockLocalTime2),
        contents = "2022-04-04 12:0:0"
    ),
    mockDefaultDiary.copy(
        date = LocalDateTime.of(mockLocalDate2, mockLocalTime1),
        contents = "2022-04-05 3:0:0"
    ),
    mockDefaultDiary.copy(
        date = LocalDateTime.of(mockLocalDate3, mockLocalTime1),
        contents = "2022-05-06 3:0:0"
    )
)