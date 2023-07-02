package com.example.data.source.user

import com.example.data.model.UserEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserMongoDataSourceImpl(
    private val db: CoroutineDatabase
):UserMongoDataSource {

    val users = db.getCollection<UserEntity>()
    override suspend fun insertUser(userEntity: UserEntity) {
        users.insertOne(userEntity)
    }


    override suspend fun findUserByEmail(email: String): UserEntity? {
        val userFilter = Filters.eq("_id", email)
        return users.findOne(userFilter)
    }

}