package com.android.pos.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    /**
     * [getAllUsersStream] get all users from database
     */
    fun getAllUsersStream(): Flow<List<User>>

    /**
     * [getUserStream] get user by username
     */
    fun getUserStream(id: Int): Flow<User>

    /**
     * [getUserStream] get user by user name
     */
    fun getUserStream(username: String): Flow<User>

    /**
     * [insertUser] insert new user
     */
    suspend fun insertUser(user: User): Long

    /**
     * [deleteUser] delete user from the database
     */
    suspend fun deleteUser(user: User)

    /**
     * [updateUser] update user from the database
     */
    suspend fun updateUser(user: User)
}