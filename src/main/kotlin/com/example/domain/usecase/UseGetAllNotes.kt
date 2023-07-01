package com.example.domain.usecase

import com.example.domain.model.Note
import com.example.domain.model.toNote
import com.example.domain.repository.NotesRepository

class UseGetAllNotes(
    private val notesRepository: NotesRepository
) {

    suspend fun execute():List<Note>{
        return notesRepository.getNotes().map { it.toNote() }
    }

}