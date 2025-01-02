package com.android.pos.data

import kotlinx.coroutines.flow.Flow

/**
 * [OfflineUserRepository]
 */
class OfflineUserRepository(private val userDao: UserDao): UserRepository {
    override fun getAllUsersStream() = userDao.getAllUsers()

    override fun getUserStream(id: Int) = userDao.getUser(id)

    override fun getUserStream(username: String) = userDao.getUser(username)

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateUser(user: User) = userDao.update(user)
}