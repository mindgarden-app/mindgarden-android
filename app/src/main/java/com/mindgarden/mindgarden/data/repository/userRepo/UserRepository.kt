package com.mindgarden.mindgarden.data.repository.userRepo

import com.mindgarden.mindgarden.data.model.entity.User
import io.reactivex.rxjava3.core.Completable

// TODO("수정 필요")
interface UserRepository {
    fun registerUser(user: User): Completable
    fun updateUser(user: User): Completable // change password or user name
    fun deleteUser(user: User): Completable
}