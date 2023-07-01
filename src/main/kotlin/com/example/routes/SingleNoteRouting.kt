package com.example.routes

import com.example.domain.model.Note
import com.example.domain.usecase.*
import com.example.utils.Constants.EMPTY_TEXT_MESSAGE
import com.example.utils.Constants.MISSING_ID_MESSAGE
import com.example.utils.Constants.SUCCESS_MESSAGE
import com.example.utils.Resource
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Routing.singleNoteRouting(
    useInsertNote: UseInsertNote,
    useDeleteNoteById: UseDeleteNoteById,
    useUpdateNote: UseUpdateNote,
    useFindNoteById: UseFindNoteById
){

    route("/singleNote"){

        post {
            val note = call.receive<Note>()
            if(note.text.isEmpty()) call.respondText(EMPTY_TEXT_MESSAGE, status =  HttpStatusCode.LengthRequired)
            if(note.title.isEmpty()) call.respondText(EMPTY_TEXT_MESSAGE, status =  HttpStatusCode.LengthRequired)
            useInsertNote.execute(note)
            call.respondText(SUCCESS_MESSAGE, status =  HttpStatusCode.OK)
        }

        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(MISSING_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            when(val res = useDeleteNoteById.execute(id)){
                is Resource.Success -> call.respondText(SUCCESS_MESSAGE, status = HttpStatusCode.OK)
                is Resource.Error -> call.respondText(res.message!!, status = HttpStatusCode.OK)
                else -> {}
            }
        }

        get("{id?}"){
            val id = call.parameters["id"] ?: return@get call.respondText(MISSING_ID_MESSAGE, status = HttpStatusCode.BadRequest)
            when(val res = useFindNoteById.execute(id)){
                is Resource.Success -> call.respond(status = HttpStatusCode.OK, res.data!!)
                is Resource.Error -> call.respondText(res.message!!, status = HttpStatusCode.OK)
                else -> {}
            }
        }

        put {
            val note = call.receive<Note>()
            when(val res = useUpdateNote.execute(note)){
                is Resource.Success -> call.respond(status = HttpStatusCode.OK, SUCCESS_MESSAGE)
                is Resource.Error -> call.respondText(res.message!!, status = HttpStatusCode.OK)
                else -> {}
            }
        }

    }


}