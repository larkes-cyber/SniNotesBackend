package com.example.routes

import com.example.domain.model.Login
import com.example.domain.model.User
import com.example.domain.model.UserData
import com.example.domain.usecase.*
import com.example.utils.Constants
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.text.Regex

fun Routing.userRouting(
    useGenerateToken: UseGenerateToken,
    useGeneratePasswordHash: UseGeneratePasswordHash,
    useInsertUser: UseInsertUser,
    useFindUserByEmail: UseFindUserByEmail,
    useCheckExistUser: UseCheckExistUser
){
    route("/user"){
        get {
            val session = call.parameters["session"] ?: return@get call.respondText(Constants.MISSED_SESSION_MESSAGE, status = HttpStatusCode.BadRequest)
            val email = call.parameters["email"] ?: return@get call.respondText(Constants.MISSED_SESSION_MESSAGE, status = HttpStatusCode.BadRequest)
            val user = useFindUserByEmail.execute(email) ?: return@get call.respondText("Non - existent user!")
            if(useCheckExistUser.execute(email = email, session = session)){
                call.respond(status = HttpStatusCode.OK, UserData(
                    login = user.email,
                    name = user.userName,
                    password = user.password,
                    session = session
                ))
            }
            else call.respondText("Incorrect session!", status = HttpStatusCode.Unauthorized)
        }

        post("/register") {
            val emailPattern = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
            val user = call.receive<User>() // ?: call.respondText("incorrect user data", status = HttpStatusCode.UnprocessableEntity)
            if(useFindUserByEmail.execute(user.email) != null){
                call.respondText("User is already exist!", status = HttpStatusCode.Unauthorized)
                return@post
            }
            if (user.email.isEmpty() || user.password.isEmpty() || user.userName.isEmpty()){
                call.respondText("Empty field!", status = HttpStatusCode.Unauthorized)
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
            if(!emailPattern.matches(user.email)){
                call.respondText("Email isn't correct!", status = HttpStatusCode.Unauthorized)
                return@post
            }
            if(user.userName.length < 3){
                call.respondText("Short name!", status = HttpStatusCode.Unauthorized)
                return@post
            }

            user.password = useGeneratePasswordHash.execute(user.password)
            useInsertUser.execute(user)
            call.respond(useGenerateToken.execute(user))
        }

        post("/authorization") {
            val login = call.receive<Login>()
            if(login.email.isEmpty() || login.password.isEmpty()) return@post call.respondText("Empty field!", status = HttpStatusCode.Unauthorized)
            val user = useFindUserByEmail.execute(login.email) ?: return@post call.respondText("Non - existent user!", status = HttpStatusCode.Unauthorized)
            val savedUserToken = useGenerateToken.execute(user)
            val currentUserToken = useGenerateToken.execute(User(email = login.email, password = useGeneratePasswordHash.execute(login.password)))
            if(savedUserToken == currentUserToken) call.respond(savedUserToken)
            else call.respondText("Incorrect password!", status = HttpStatusCode.Unauthorized)
        }

    }
}