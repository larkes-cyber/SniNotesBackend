package com.example.utils

import javax.crypto.spec.SecretKeySpec

object Constants {

    const val SUCCESS_MESSAGE = "success"
    const val MISSED_ID_MESSAGE = "missing necessary user data"
    const val MISSED_SESSION_MESSAGE = "missed session"
    const val MISSED_EMAIL_MESSAGE = "missed email"
    const val EMPTY_TEXT_MESSAGE = "empty text"
    const val INCORRECT_SESSION_MESSAGE = "incorrect session"
    const val ISSUER = "SniNotes"
    const val SUBJECT = "sniauth"
    val JWT_SECRET_KEY = "JWT_SECRET"
    val HASH_SECRET_KEY = "JWT_SECRET"
    val HMACKEY = SecretKeySpec(HASH_SECRET_KEY.toByteArray(), "HmacSHA1")

}