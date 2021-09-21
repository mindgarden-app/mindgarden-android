package com.mindgarden.mindgarden.data.model.entity

import androidx.room.*
import com.mindgarden.mindgarden.data.model.local.Tree
import java.util.*

@Entity
data class Garden(
    @PrimaryKey
    @ColumnInfo(name = "idx")
    val idx: Long,
    @ColumnInfo(name = "date")
    val date: Date,
    @ColumnInfo(name= "minds")
    val minds: Map<Int, Tree>
)