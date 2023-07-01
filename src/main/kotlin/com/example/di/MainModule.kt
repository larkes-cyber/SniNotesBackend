package com.example.di

import com.example.data.repository.DataNotesRepository
import com.example.data.source.NotesMongoDataSource
import com.example.data.source.NotesMongoDataSourceImpl
import com.example.domain.repository.NotesRepository
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


}