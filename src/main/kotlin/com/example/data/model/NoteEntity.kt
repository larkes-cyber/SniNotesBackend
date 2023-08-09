package com.example.data.model

import org.bson.codecs.pojo.annotations.BsonId

data class NoteEntity(
    @BsonId
    var id:String = "",
    val text:String,
    val title:String,
    val color:Long,
    val session:String,
    val timestamp:Long
)