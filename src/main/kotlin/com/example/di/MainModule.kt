package com.example.di

import com.example.data.repository.DataNotesRepository
import com.example.data.repository.DataUserRepository
import com.example.data.source.notes.NotesMongoDataSource
import com.example.data.source.notes.NotesMongoDataSourceImpl
import com.example.data.source.user.UserAuthDataSource
import com.example.data.source.user.UserAuthDataSourceImpl
import com.example.domain.repository.NotesRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.*
import org.koin.dsl.module
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.coroutine.coroutine


val mainModule = module {

    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("notes_db_yt")

    }

    single<NotesMongoDataSource> {
        NotesMongoDataSourceImpl(get())
    }

    single<NotesRepository> {
        DataNotesRepository(get())
    }

    single<UserAuthDataSource> {
        UserAuthDataSourceImpl()
    }

    single<UserRepository> {
        DataUserRepository(get())
    }

    factory {
        UseGetAllNotes(get())
    }

    factory {
        UseInsertNote(get())
    }

    factory {
        UseDeleteNoteById(get())
    }
    factory {
        UseUpdateNote(get())
    }
    factory {
        UseFindNoteById(get())
    }
    factory {
        UseGeneratePasswordHash(get())
    }
    factory {
        UseGenerateToken(get())
    }


}