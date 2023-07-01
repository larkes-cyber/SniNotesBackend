package com.example.data.repository

import com.example.data.model.UserEntity
import com.example.data.source.user.UserAuthDataSource
import com.example.domain.repository.UserRepository

class DataUserRepository(
    private val userAuthDataSource: UserAuthDataSource
):UserRepository {
    override fun generateToken(user: UserEntity): String {
        return userAuthDataSource.generateToken(user)
    }

    override fun generatePasswordHash(password: String): String {
        return userAuthDataSource.generatePasswordHash(password)
    }
}