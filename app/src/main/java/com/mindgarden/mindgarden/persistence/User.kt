package com.mindgarden.mindgarden.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name="nickname")
    val nickname:String,
    @ColumnInfo(name="password")
    val password:String
)