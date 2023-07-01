package com.example.domain.model

import com.example.data.model.UserEntity

fun User.toUserEntity():UserEntity{
    return UserEntity(
        id = email,
        email = email,
        password = password,
        userName = userName
    )
}

fun UserEntity.toUser():User{
    return User(
        email = email,
        password = password,
        userName = userName
    )
}