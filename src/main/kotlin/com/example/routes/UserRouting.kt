package com.example.routes

import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.domain.usecase.UseFindUserByEmail
import com.example.domain.usecase.UseGeneratePasswordHash
import com.example.domain.usecase.UseGenerateToken
import com.example.domain.usecase.UseInsertUser
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.userRouting(
    useGenerateToken: UseGenerateToken,
    useGeneratePasswordHash: UseGeneratePasswordHash,
    useInsertUser: UseInsertUser,
    useFindUserByEmail: UseFindUserByEmail
){
    route("/user"){

        post("/register") {
            val user = call.receive<User>() // ?: call.respondText("incorrect user data", status = HttpStatusCode.UnprocessableEntity)
            if(useFindUserByEmail.execute(user.email) != null){
                call.respondText("User is already exist!", status = HttpStatusCode.Unauthorized)
                return@post
            }
            if(user.password.length < 8){
                call.respondText("Short password!", status = HttpStatusCode.Unauthorized)
                return@post
            }
            if(user.email.length < 8){
                call.respondText("Short email!", status = HttpStatusCode.Unauthorized)
                return@post
            }
            user.password = useGeneratePasswordHash.execute(user.password)
            useInsertUser.execute(user)
            call.respond(useGenerateToken.execute(user))
        }

        post("/authorization") {
            val login = call.receive<Login>()
            val user = useFindUserByEmail.execute(login.email) ?: return@post call.respondText("Non - existent user!")
            val savedUserToken = useGenerateToken.execute(user)
            val currentUserToken = useGenerateToken.execute(User(email = login.email, password = useGeneratePasswordHash.execute(login.password)))
            return@post if(savedUserToken == currentUserToken) call.respond(savedUserToken)
                        else call.respondText("Incorrect login!", status = HttpStatusCode.Unauthorized)
        }

    }
}