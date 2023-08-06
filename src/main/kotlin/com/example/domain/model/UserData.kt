package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val login:String,
    val password:String,
    val session:String,
    val name:String
)