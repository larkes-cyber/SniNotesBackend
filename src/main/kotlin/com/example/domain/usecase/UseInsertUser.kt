package com.example.domain.usecase

import com.example.data.repository.DataUserRepository
import com.example.domain.model.User
import com.example.domain.model.toUserEntity
import com.example.domain.repository.UserRepository

class UseInsertUser(
    private val userRepository: UserRepository
) {

    suspend fun execute(user:User){
        userRepository.insertUser(user.toUserEntity())
    }

}