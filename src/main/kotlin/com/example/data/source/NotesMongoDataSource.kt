package com.example.data.source

import com.example.data.model.NoteEntity

interface NotesMongoDataSource {

    suspend fun insertNote(note:NoteEntity)
    suspend fun getAllNotes():List<NoteEntity>
    suspend fun deleteNoteById(id:String)
    suspend fun findNoteById(id:String):NoteEntity
    suspend fun updateNote(note: NoteEntity)

}