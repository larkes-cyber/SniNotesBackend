package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Notes(
    val notes:List<Note>
)