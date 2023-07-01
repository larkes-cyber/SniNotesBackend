package com.example.domain.usecase

import com.example.domain.model.toNoteEntity
import com.example.domain.repository.NotesRepository
import com.example.utils.Constants
import com.example.utils.Resource

class UseDeleteNoteById(
    private val notesRepository: NotesRepository
) {

    suspend fun execute(id:String):Resource<*>{
        return try {
            notesRepository.deleteNoteById(id)
            Resource.Success(Constants.SUCCESS_MESSAGE)
        }catch (e:Exception){
            Resource.Error<String>(e.message.toString())
        }
    }

}