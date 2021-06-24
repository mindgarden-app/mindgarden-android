package com.mindgarden.mindgarden.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

class Tree (
    val date: Date,
    val location: Int,
    val treeIdx: Int
)