package com.example.domain.model

import com.example.data.model.NoteEntity

fun Note.toNoteEntity(session:String):NoteEntity{
    return if(id == null) {
        NoteEntity(
            text = text,
            title = title,
            color = color,
            session = session
        )
    }else {
        NoteEntity(
            text = text,
            title = title,
            id = id,
            color = color,
            session = session
        )
    }
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        text = text,
        color = color
    )
}