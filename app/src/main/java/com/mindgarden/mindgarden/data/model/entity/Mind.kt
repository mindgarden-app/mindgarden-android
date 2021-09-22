package com.mindgarden.mindgarden.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Mind(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idx")
    val idx: Long,
    val treeIdx: Int,
    val gardenDate: LocalDateTime, // e.g. 2021-09-01T00:00
    val mindDate: LocalDateTime, // e.g. 2021-09-22T07:46:14.231
    val location: Int,
)