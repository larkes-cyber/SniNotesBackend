package com.example.routes

import com.example.domain.model.Note
import com.example.domain.usecase.*
import com.example.utils.Constants
import com.example.utils.Constants.EMPTY_TEXT_MESSAGE
import com.example.utils.Constants.MISSED_ID_MESSAGE
import com.example.utils.Constants.SUCCESS_MESSAGE
import com.example.utils.Resource
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Routing.singleNoteRouting(
    useInsertNote: UseInsertNote,
    useDeleteNote: UseDeleteNote,
    useUpdateNote: UseUpdateNote,
    useFindNoteById: UseFindNoteById,
    useCheckExistUser: UseCheckExistUser

){

    route("/singleNote"){

        post("{session?}/{email?}") {
            val note = call.receive<Note>()
            if(note.text.isEmpty()) call.respondText(EMPTY_TEXT_MESSAGE, status =  HttpStatusCode.LengthRequired)
            if(note.title.isEmpty()) call.respondText(EMPTY_TEXT_MESSAGE, status =  HttpStatusCode.LengthRequired)

            val session = call.parameters["session"] ?: return@post call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            val email = call.parameters["email"] ?: return@post call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            if(!useCheckExistUser.execute(email = email, session = session)) return@post  call.respondText(Constants.INCORRECT_SESSION_MESSAGE, status = HttpStatusCode.BadRequest )

            useInsertNote.execute(note)
            call.respondText(SUCCESS_MESSAGE, status =  HttpStatusCode.OK)
        }

        delete("{id?}/{session?}/{email?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)

            val session = call.parameters["session"] ?: return@delete call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            val email = call.parameters["email"] ?: return@delete call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            if(!useCheckExistUser.execute(email = email, session = session)) return@delete  call.respondText(Constants.INCORRECT_SESSION_MESSAGE, status = HttpStatusCode.BadRequest )

            when(val res = useDeleteNote.execute(id = id, session = session)){
                is Resource.Success -> call.respondText(SUCCESS_MESSAGE, status = HttpStatusCode.OK)
                is Resource.Error -> call.respondText(res.message!!, status = HttpStatusCode.OK)
                else -> {}
            }
        }

        get("{id?}/{session?}/{email?}"){
            val id = call.parameters["id"] ?: return@get call.respondText(MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)

            val session = call.parameters["session"] ?: return@get call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            val email = call.parameters["email"] ?: return@get call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            if(!useCheckExistUser.execute(email = email, session = session)) return@get  call.respondText(Constants.INCORRECT_SESSION_MESSAGE, status = HttpStatusCode.BadRequest )

            when(val res = useFindNoteById.execute(id)){
                is Resource.Success -> call.respond(status = HttpStatusCode.OK, res.data!!)
                is Resource.Error -> call.respondText(res.message!!, status = HttpStatusCode.OK)
                else -> {}
            }
        }

        put("{session?}/{email?}") {
            val note = call.receive<Note>()

            val session = call.parameters["session"] ?: return@put call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            val email = call.parameters["email"] ?: return@put call.respondText(Constants.MISSED_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            if(!useCheckExistUser.execute(email = email, session = session)) return@put  call.respondText(Constants.INCORRECT_SESSION_MESSAGE, status = HttpStatusCode.BadRequest )

            when(val res = useUpdateNote.execute(note)){
                is Resource.Success -> call.respond(status = HttpStatusCode.OK, SUCCESS_MESSAGE)
                is Resource.Error -> call.respondText(res.message!!, status = HttpStatusCode.OK)
                else -> {}
            }
        }

    }


}