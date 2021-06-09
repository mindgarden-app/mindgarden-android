package com.mindgarden.mindgarden.data.source.local

import androidx.room.*
import com.mindgarden.mindgarden.data.model.User

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(user: User)

    @Update
    fun changePassword(user: User):Int

    @Delete
    fun deleteUser(user: User):Int
}