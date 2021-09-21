package com.mindgarden.mindgarden.data.repository.userRepo.local.dao

import androidx.room.*
import com.mindgarden.mindgarden.data.model.entity.User
import com.mindgarden.mindgarden.data.repository.common.BaseDao

@Dao
abstract class UserDao : BaseDao<User> {

    // TODO : getUser query 작성
    // @Query("")
    // abstract fun getUser(): Single<User>
}