package com.example.utils

import javax.crypto.spec.SecretKeySpec

object Constants {

    const val SUCCESS_MESSAGE = "success"
    const val MISSING_ID_MESSAGE = "missing id"
    const val EMPTY_TEXT_MESSAGE = "empty text"
    const val ISSUER = "SniNotes"
    const val SUBJECT = "sniauth"
    val JWT_SECRET_KEY = System.getenv("JWT_SECRET")
    val HASH_SECRET_KEY = System.getenv("HASH_SECRET_KEY")
    val HMACKEY = SecretKeySpec(HASH_SECRET_KEY.toByteArray(), "HmacSHA1")

}