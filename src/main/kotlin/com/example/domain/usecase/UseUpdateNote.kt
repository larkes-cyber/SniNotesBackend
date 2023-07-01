package com.example.domain.usecase

import com.example.domain.model.Note
import com.example.domain.model.toNoteEntity
import com.example.domain.repository.NotesRepository
import com.example.utils.Constants.SUCCESS_MESSAGE
import com.example.utils.Resource

class UseUpdateNote(
    private val notesRepository: NotesRepository
) {

    suspend fun execute(note: Note):Resource<*>{

        return try {
            notesRepository.updateNote(note.toNoteEntity())
            Resource.Success(SUCCESS_MESSAGE)
        }catch (e:Exception){
            Resource.Error<String>(e.message.toString())
        }

    }

}