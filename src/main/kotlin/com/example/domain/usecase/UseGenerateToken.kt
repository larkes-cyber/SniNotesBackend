package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.model.toUserEntity
import com.example.domain.repository.UserRepository

class UseGenerateToken(
    private val userRepository: UserRepository
) {

    fun execute(user:User):String{
        return userRepository.generateToken(user.toUserEntity())
    }

}