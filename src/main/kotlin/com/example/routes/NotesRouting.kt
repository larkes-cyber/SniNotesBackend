package com.example.routes

import com.example.domain.usecase.UseGetAllNotes
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.notesRouting(
    useGetAllNotes: UseGetAllNotes
){

    route("/notes"){

        get {
            call.respond(HttpStatusCode.OK, useGetAllNotes.execute())
        }

    }

}