package com.example.domain.repository

import com.example.data.model.NoteEntity
import com.example.domain.model.Note

interface NotesRepository {

    suspend fun insertNote(note:NoteEntity)
    suspend fun getNotes(session:String):List<NoteEntity>
    suspend fun deleteNoteById(id:String, session:String)
    suspend fun findNoteById(id:String):NoteEntity
    suspend fun updateNote(note:NoteEntity)

}