package com.example.domain.usecase

import com.example.domain.model.Note
import com.example.domain.model.toNote
import com.example.domain.repository.NotesRepository
import com.example.utils.Resource

class UseFindNoteById(
    private val notesRepository: NotesRepository
) {

    suspend fun execute(id:String):Resource<Note>{

        return try {
            val note = notesRepository.findNoteById(id)
            Resource.Success(note.toNote())
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }

    }

}