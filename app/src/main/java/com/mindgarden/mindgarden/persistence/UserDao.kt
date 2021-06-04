package com.mindgarden.mindgarden.persistence

import androidx.room.*

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(user:User)

    @Update
    fun changePassword(user: User)

    @Delete
    fun deleteUser(user:User)
}