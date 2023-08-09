package com.example.data.source.notes

import com.example.data.model.NoteEntity
import com.mongodb.client.model.Filters
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase

class NotesMongoDataSourceImpl(
    private val db: CoroutineDatabase
): NotesMongoDataSource {

    private val notes = db.getCollection<NoteEntity>()
    override suspend fun insertNote(note: NoteEntity):String {
        val generatedId = ObjectId().toString()
        note.id = generatedId
        notes.insertOne(note)
        return generatedId
    }

    override suspend fun getAllNotes(session:String): List<NoteEntity> {
        val filter = Filters.eq("session", session)
        return notes.find(filter)
            .descendingSort(NoteEntity::color)
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

    override suspend fun updateNote(note: NoteEntity, session: String) {
        val filter = Filters.eq("_id", note.id)
        val filter2 = Filters.eq("session", session)
        notes.replaceOne(Filters.and(filter, filter2), note)
    }
}