package com.example.data.source.user

import com.example.data.model.UserEntity

interface UserAuthDataSource {

    fun generateToken(user:UserEntity):String
    fun generatePasswordHash(password:String):String

}