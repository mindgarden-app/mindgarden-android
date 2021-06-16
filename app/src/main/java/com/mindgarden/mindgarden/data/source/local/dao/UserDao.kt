package com.mindgarden.mindgarden.data.source.local.dao

import androidx.room.*
import com.mindgarden.mindgarden.data.model.User

@Dao
abstract class UserDao : BaseDao<User>