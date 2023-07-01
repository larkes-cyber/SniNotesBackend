package com.example.domain.model

import com.example.data.model.NoteEntity

fun Note.toNoteEntity():NoteEntity{
    return if(id == null) {
        NoteEntity(
            text = text,
            title = title,
            timestamp = timestamp
        )
    }else {
        NoteEntity(
            text = text,
            title = title,
            id = id,
            timestamp = timestamp
        )
    }
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        text = text,
        timestamp = timestamp
    )
}