package com.example.di

import com.example.data.repository.DataNotesRepository
import com.example.data.repository.DataUserRepository
import com.example.data.source.notes.NotesMongoDataSource
import com.example.data.source.notes.NotesMongoDataSourceImpl
import com.example.data.source.auth.UserAuthDataSource
import com.example.data.source.auth.UserAuthDataSourceImpl
import com.example.data.source.user.UserMongoDataSource
import com.example.data.source.user.UserMongoDataSourceImpl
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

    single<UserAuthDataSource> {
        UserAuthDataSourceImpl()
    }

    single<UserMongoDataSource> {
        UserMongoDataSourceImpl(get())
    }

    single<NotesRepository> {
        DataNotesRepository(get())
    }



    single<UserRepository> {
        DataUserRepository(
            userAuthDataSource =  get(),
            userMongoDataSource = get()
        )
    }

    factory {
        UseGetAllNotes(get())
    }
    factory {
        UseInsertNote(get())
    }
    factory {
        UseDeleteNote(get())
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
    factory {
        UseInsertUser(get())
    }
    factory {
        UseFindUserByEmail(get())
    }
    factory {
        UseCheckExistUser(get(),get())
    }

}