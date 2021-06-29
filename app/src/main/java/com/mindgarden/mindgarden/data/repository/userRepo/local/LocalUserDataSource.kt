package com.mindgarden.mindgarden.data.repository.userRepo.local

import com.mindgarden.mindgarden.data.model.entity.User
import com.mindgarden.mindgarden.data.repository.userRepo.UserDataSource
import com.mindgarden.mindgarden.data.repository.userRepo.local.dao.UserDao
import io.reactivex.rxjava3.core.Completable

class LocalUserDataSource(private val userDao: UserDao) : UserDataSource {
    override fun registerUser(user: User): Completable {
        return userDao.insert(user)
    }

    override fun updateUser(user: User): Completable {
        return userDao.update(user)
    }

    override fun deleteUser(user: User): Completable {
        return userDao.delete(user)
    }
}