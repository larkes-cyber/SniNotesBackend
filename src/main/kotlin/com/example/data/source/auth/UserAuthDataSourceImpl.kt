package com.example.data.source.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.model.UserEntity
import com.example.utils.Constants.HMACKEY
import com.example.utils.Constants.ISSUER
import com.example.utils.Constants.JWT_SECRET_KEY
import com.example.utils.Constants.SUBJECT
import io.ktor.util.*
import javax.crypto.Mac

class UserAuthDataSourceImpl:UserAuthDataSource {

    private val algorithm = Algorithm.HMAC512(JWT_SECRET_KEY)
    private val verifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .build()

    override fun generateToken(user: UserEntity): String {
        return JWT.create()
            .withSubject(SUBJECT)
            .withIssuer(ISSUER)
            .withClaim("email", user.email)
            .withClaim("password", user.password)
            .sign(algorithm)
    }

    override fun generatePasswordHash(password: String): String {
        val hmac = Mac.getInstance("HmacSHA1")
        hmac.init(HMACKEY)
        return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
    }


}