package com.example.domain.usecase

import com.example.domain.model.Login
import com.example.domain.model.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class UseCheckExistUser(
    private val useGenerateToken: UseGenerateToken,
    private val useFindUserByEmail: UseFindUserByEmail
) {

    suspend fun execute(email:String, session:String):Boolean{
        val user = useFindUserByEmail.execute(email) ?: return false
        val savedUserToken = useGenerateToken.execute(user)
        return savedUserToken == session

    }

}