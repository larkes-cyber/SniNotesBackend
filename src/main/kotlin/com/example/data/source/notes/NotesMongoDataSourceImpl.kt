package com.example.data.source.notes

import com.example.data.model.NoteEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class NotesMongoDataSourceImpl(
    private val db: CoroutineDatabase
): NotesMongoDataSource {

    private val notes = db.getCollection<NoteEntity>()
    override suspend fun insertNote(note: NoteEntity) {
       notes.insertOne(note)
    }

    override suspend fun getAllNotes(session:String): List<NoteEntity> {
        val filter = Filters.eq("session", session)
        return notes.find(filter)
            .descendingSort(NoteEntity::timestamp)
            .toList()
    }

    override suspend fun deleteNoteById(id:String, session:String) {
        val filter1 = Filters.eq("_id", id)
        val filter2 = Filters.eq("session", session)
        notes.deleteOne(Filters.and(filter1, filter2))
    }

    override suspend fun findNoteById(id: String): NoteEntity {
        val filter = Filters.eq("_id", id)
        return notes.findOne(filter)!!
    }

    override suspend fun updateNote(note: NoteEntity) {
        val filter = Filters.eq("_id", note.id)
        val filter2 = Filters.eq("session", note.session)
        notes.replaceOne(Filters.and(filter, filter2), note)
    }
}