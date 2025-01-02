package com.android.pos.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.pos.data.User
import com.android.pos.data.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class SignUpViewModel(private val userRepository: UserRepository): ViewModel() {
    val SignUpState = SignUpUiState()

    fun createAccount() {
//        userRepository.createAccount()
    }

    suspend fun createAccount_test() {
        val user = userRepository.getUserStream("Laplala").firstOrNull()
        if (user != null) {
            Log.d("SignUpViewModel", "createAccount_test: user exist")
            return
        }

        val newUser = User(name = "Laplala", password = "123456")

        userRepository.insertUser(newUser)
        Log.d("SignUpViewModel", "createAccount_test: ${newUser.id} created")
    }
}

data class SignUpUiState(
    val accState: AccState = AccState(),
    val isValidate: Boolean = false,
)

data class AccState(
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)

fun  AccState.isValid() : Boolean {
    if (username.isEmpty()) return false
    if (password.isEmpty()) return false
    if (confirmPassword.isEmpty()) return false
    if (password != confirmPassword) return false
    return true
}

fun  AccState.toUser() = User(
    name = username,
    password = password,
)