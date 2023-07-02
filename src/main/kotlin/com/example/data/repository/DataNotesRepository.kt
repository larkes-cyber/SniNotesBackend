package com.example.data.repository

import com.example.data.model.NoteEntity
import com.example.data.source.notes.NotesMongoDataSource
import com.example.domain.repository.NotesRepository

class DataNotesRepository(
    private val notesMongoDataSource: NotesMongoDataSource
):NotesRepository {
    override suspend fun insertNote(note: NoteEntity) {
        notesMongoDataSource.insertNote(note)
    }

    override suspend fun getNotes(session:String): List<NoteEntity> {
        return notesMongoDataSource.getAllNotes(session)
    }

    override suspend fun deleteNoteById(id:String, session:String) {
        notesMongoDataSource.deleteNoteById(session = session, id = id)
    }

    override suspend fun findNoteById(id: String): NoteEntity {
        return notesMongoDataSource.findNoteById(id)
    }

    override suspend fun updateNote(note: NoteEntity) {
        notesMongoDataSource.updateNote(note)
    }
}