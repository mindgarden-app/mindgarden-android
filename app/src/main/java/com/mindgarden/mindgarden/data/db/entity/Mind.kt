package com.mindgarden.mindgarden.data.db.entity

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
    val location: Int,
    val date: LocalDateTime, // 2021-09-22T07:46:14.231
)