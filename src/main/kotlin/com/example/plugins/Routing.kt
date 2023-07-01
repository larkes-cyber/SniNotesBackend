package com.example.plugins

import com.example.domain.usecase.*
import com.example.routes.notesRouting
import com.example.routes.singleNoteRouting
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val useInsertNote by inject<UseInsertNote>()
    val useGetAllNotes by inject<UseGetAllNotes>()
    val useDeleteNoteById by inject<UseDeleteNoteById>()
    val useFindNoteById by inject<UseFindNoteById>()
    val useUpdateNote by inject<UseUpdateNote>()

    install(Routing){
        singleNoteRouting(
            useInsertNote = useInsertNote,
            useDeleteNoteById = useDeleteNoteById,
            useFindNoteById = useFindNoteById,
            useUpdateNote = useUpdateNote
        )
        notesRouting(
            useGetAllNotes = useGetAllNotes
        )
    }
}
