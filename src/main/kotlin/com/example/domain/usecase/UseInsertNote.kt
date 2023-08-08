package com.example.domain.usecase

import com.example.domain.model.Note
import com.example.domain.model.toNoteEntity
import com.example.domain.repository.NotesRepository

class UseInsertNote(
    private val notesRepository: NotesRepository
) {

    suspend fun execute(note:Note, session:String):String{
        return notesRepository.insertNote(note.toNoteEntity(session))
    }

}