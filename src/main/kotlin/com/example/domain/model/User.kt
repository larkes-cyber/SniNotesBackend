package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email:String,
    var password:String,
    val userName:String
)