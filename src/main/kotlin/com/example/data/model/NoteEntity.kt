package com.example.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class NoteEntity(
    @BsonId
    val id:String = ObjectId().toString(),
    val text:String,
    val title:String,
    val timestamp:Long

)