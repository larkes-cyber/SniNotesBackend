package com.example.plugins

import com.example.domain.usecase.*
import com.example.routes.notesRouting
import com.example.routes.singleNoteRouting
import com.example.routes.userRouting
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val useInsertNote by inject<UseInsertNote>()
    val useGetAllNotes by inject<UseGetAllNotes>()
    val useDeleteNote by inject<UseDeleteNote>()
    val useFindNoteById by inject<UseFindNoteById>()
    val useUpdateNote by inject<UseUpdateNote>()
    val useGeneratePasswordHash by inject<UseGeneratePasswordHash>()
    val useGenerateToken by inject<UseGenerateToken>()
    val useInsertUser by inject<UseInsertUser>()
    val useFindUserByEmail by inject<UseFindUserByEmail>()
    val useCheckExistUser by inject<UseCheckExistUser>()

    install(Routing){
        singleNoteRouting(
            useInsertNote = useInsertNote,
            useDeleteNote = useDeleteNote,
            useFindNoteById = useFindNoteById,
            useUpdateNote = useUpdateNote,
            useCheckExistUser = useCheckExistUser
        )
        notesRouting(
            useGetAllNotes = useGetAllNotes,
            useCheckExistUser = useCheckExistUser
        )
        userRouting(
            useGeneratePasswordHash = useGeneratePasswordHash,
            useGenerateToken = useGenerateToken,
            useFindUserByEmail = useFindUserByEmail,
            useInsertUser = useInsertUser,
            useCheckExistUser = useCheckExistUser
        )
    }
}
