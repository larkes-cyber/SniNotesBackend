package com.example.data.repository

import com.example.data.model.UserEntity
import com.example.data.source.auth.UserAuthDataSource
import com.example.data.source.user.UserMongoDataSource
import com.example.domain.repository.UserRepository

class DataUserRepository(
    private val userAuthDataSource: UserAuthDataSource,
    private val userMongoDataSource: UserMongoDataSource
):UserRepository {
    override fun generateToken(user: UserEntity): String {
        return userAuthDataSource.generateToken(user)
    }

    override fun generatePasswordHash(password: String): String {
        return userAuthDataSource.generatePasswordHash(password)
    }

    override suspend fun insertUser(user: UserEntity) {
        userMongoDataSource.insertUser(user)
    }

    override suspend fun findUserByEmail(email: String): UserEntity? {
        return userMongoDataSource.findUserByEmail(email)
    }
}