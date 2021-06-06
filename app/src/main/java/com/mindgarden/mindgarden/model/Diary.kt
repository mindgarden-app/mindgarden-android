package com.mindgarden.mindgarden.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class Diary(
    @ColumnInfo(name="content")
    val content:String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idx")
    var idx:Long = 0
}