package com.example.data.source.notes

import com.example.data.model.NoteEntity

interface NotesMongoDataSource {

    suspend fun insertNote(note:NoteEntity)
    suspend fun getAllNotes(session:String):List<NoteEntity>
    suspend fun deleteNoteById(id:String, session:String)
    suspend fun findNoteById(id:String):NoteEntity
    suspend fun updateNote(note: NoteEntity)

}