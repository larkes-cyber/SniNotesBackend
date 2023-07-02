package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.model.toUser
import com.example.domain.repository.UserRepository

class UseFindUserByEmail(
    private val userRepository: UserRepository
) {

    suspend fun execute(email:String):User?{
        return userRepository.findUserByEmail(email)?.toUser()
    }

}