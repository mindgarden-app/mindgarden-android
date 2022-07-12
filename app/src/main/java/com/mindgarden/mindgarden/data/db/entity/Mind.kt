package com.mindgarden.mindgarden.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mindgarden.mindgarden.presentation.inventory.model.Tree
import java.time.LocalDateTime

@Entity
data class Mind(
    val tree: Tree,
    val location: Int,
    val date: LocalDateTime, // 2021-09-22T07:46:14.231
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Long = 0
}