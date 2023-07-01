package com.example.routes

import com.example.domain.model.User
import com.example.domain.usecase.UseGeneratePasswordHash
import com.example.domain.usecase.UseGenerateToken
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.userRouting(
    useGenerateToken: UseGenerateToken,
    useGeneratePasswordHash: UseGeneratePasswordHash
){
    route("/user"){

        post {
            val user = call.receive<User>() // ?: call.respondText("incorrect user data", status = HttpStatusCode.UnprocessableEntity)
            user.password = useGeneratePasswordHash.execute(user.password)
            call.respond(useGenerateToken.execute(user))
        }

    }
}