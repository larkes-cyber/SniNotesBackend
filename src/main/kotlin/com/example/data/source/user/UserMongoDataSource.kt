package com.example.data.source.user

import com.example.data.model.UserEntity

interface UserMongoDataSource {

    suspend fun insertUser(userEntity: UserEntity)
    suspend fun findUserByEmail(email:String):UserEntity?

}