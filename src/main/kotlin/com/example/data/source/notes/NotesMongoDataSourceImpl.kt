package com.example.data.source.notes

import com.example.data.model.NoteEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class NotesMongoDataSourceImpl(
    private val db: CoroutineDatabase
): NotesMongoDataSource {

    val notes = db.getCollection<NoteEntity>()
    override suspend fun insertNote(note: NoteEntity) {
       notes.insertOne(note)
    }

    override suspend fun getAllNotes(): List<NoteEntity> {
        return notes.find()
            .descendingSort(NoteEntity::timestamp)
            .toList()
    }

    override suspend fun deleteNoteById(id: String) {
        val filter = Filters.eq("_id", id)
        notes.deleteOne(filter)
    }

    override suspend fun findNoteById(id: String): NoteEntity {
        val filter = Filters.eq("_id", id)
        return notes.findOne(filter)!!
    }

    override suspend fun updateNote(note: NoteEntity) {
        val filter = Filters.eq("_id", note.id)
        notes.replaceOne(filter, note)
    }
}