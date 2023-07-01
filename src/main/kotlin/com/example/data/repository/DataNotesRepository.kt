package com.example.data.repository

import com.example.data.model.NoteEntity
import com.example.data.source.NotesMongoDataSource
import com.example.domain.model.Note
import com.example.domain.repository.NotesRepository

class DataNotesRepository(
    private val notesMongoDataSource: NotesMongoDataSource
):NotesRepository {
    override suspend fun insertNote(note: NoteEntity) {
        notesMongoDataSource.insertNote(note)
    }

    override suspend fun getNotes(): List<NoteEntity> {
        return notesMongoDataSource.getAllNotes()
    }

    override suspend fun deleteNoteById(id: String) {
        notesMongoDataSource.deleteNoteById(id)
    }

    override suspend fun findNoteById(id: String): NoteEntity {
        return notesMongoDataSource.findNoteById(id)
    }

    override suspend fun updateNote(note: NoteEntity) {
        notesMongoDataSource.updateNote(note)
    }
}