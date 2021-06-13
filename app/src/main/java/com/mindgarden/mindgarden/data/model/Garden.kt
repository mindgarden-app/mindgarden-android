package com.mindgarden.mindgarden.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Garden(
    @PrimaryKey
    @ColumnInfo(name = "idx")
    val idx: Long,
    @ColumnInfo(name = "date")
    val data: Date,
    @ColumnInfo(name = "location")
    val location: Int,
    @ColumnInfo(name = "treeIdx")
    val treeIdx: Int
)