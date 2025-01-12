package com.android.pos.auth

import com.android.pos.data.IAppContainer
import com.android.pos.data.User
import com.android.pos.data.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class Auth(private val appContainer: IAppContainer) {
    var user: User? = null
        get
        private set

    fun isAuthenticated(): Boolean {
        return user != null
    }

    suspend fun login(username: String, password: String): Boolean {
        val user = appContainer.userRepository.getUserStream(username).firstOrNull()

        if (user != null && user.password == password) {
            this.user = user.copy(password = "")
            appContainer.initDataContainer(user.id)
            return true
        }
        return false
    }

    fun logout() {
        appContainer.initDataContainer(0)
        user = null
    }
}