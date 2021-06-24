package com.mindgarden.mindgarden.data.repository.userRepo

import com.mindgarden.mindgarden.data.model.entity.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepository(private val localDataSource: UserDataSource): UserDataSource {

    override fun registerUser(user: User): Completable {
        return localDataSource.registerUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateUser(user: User): Completable {
        return localDataSource.updateUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteUser(user: User): Completable {
        return localDataSource.deleteUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}