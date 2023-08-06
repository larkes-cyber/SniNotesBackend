package com.example.routes

import com.example.domain.usecase.UseCheckExistUser
import com.example.domain.usecase.UseGetAllNotes
import com.example.utils.Constants
import com.example.utils.Constants.INCORRECT_SESSION_MESSAGE
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.notesRouting(
    useGetAllNotes: UseGetAllNotes,
    useCheckExistUser: UseCheckExistUser
){

    route("/notes"){



        get("{session?}/{email?}") {
            val session = call.parameters["session"] ?: return@get call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            val email = call.parameters["email"] ?: return@get call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            return@get if(useCheckExistUser.execute(email = email, session = session)){
                call.respond(HttpStatusCode.OK, useGetAllNotes.execute(session))
             }else{
                 call.respondText(INCORRECT_SESSION_MESSAGE, status = HttpStatusCode.BadRequest )
            }
        }

    }

}