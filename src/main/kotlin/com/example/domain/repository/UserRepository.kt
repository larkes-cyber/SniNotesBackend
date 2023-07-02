package com.example.domain.repository

import com.example.data.model.UserEntity
import com.example.domain.model.User

interface UserRepository {

    fun generateToken(user:UserEntity):String
    fun generatePasswordHash(password:String):String

    suspend fun insertUser(user: UserEntity)
    suspend fun findUserByEmail(email:String):UserEntity?
}