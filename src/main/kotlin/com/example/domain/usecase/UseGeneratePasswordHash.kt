package com.example.domain.usecase

import com.example.domain.repository.UserRepository

class UseGeneratePasswordHash(
    private val userRepository: UserRepository
) {

    fun execute(password: String):String{
        return userRepository.generatePasswordHash(password)
    }

}