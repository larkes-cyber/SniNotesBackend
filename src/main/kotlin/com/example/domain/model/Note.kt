package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val title:String,
    val text:String,
    val id:String?,
    val color:Long
)