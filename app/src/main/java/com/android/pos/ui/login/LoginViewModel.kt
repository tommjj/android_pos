package com.android.pos.ui.login

import android.content.SharedPreferences
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.android.pos.auth.Auth

class LoginViewModel(
    private val auth: Auth,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    var loginUiState by mutableStateOf(LoginUiState(
        inputState = LoginInputsState(
            username = sharedPreferences.getString("username", "") ?: "",
            password = sharedPreferences.getString("password", "") ?: "",
    )))
        private set

    fun onInputChanged(loginInputs: LoginInputsState) {
        loginUiState = loginUiState.copy(
            inputState = loginInputs,
            isValidate = loginInputs.isValid(),
            errorMessage = null,
        )
    }

    suspend fun submit() {
        if (loginUiState.isPadding) return;
        loginUiState = loginUiState.copy(
            isPadding = true,
        )

        val errors = loginUiState.inputState.toLoginError()
        if (errors != null) {
            loginUiState = loginUiState.copy(
                errorMessage = errors,
                isValidate = false,
                isPadding = false,
            )
            throw Exception("Invalid input")
        }

        val loginSuccess = auth.login(
            username = loginUiState.inputState.username,
            password = loginUiState.inputState.password
        )
        if (!loginSuccess) {
            loginUiState = loginUiState.copy(
                errorMessage = listOf(LoginError.CREDENTIAL_INVALID),
                isValidate = false,
                isPadding = false
            )
            throw Exception(LoginError.CREDENTIAL_INVALID.message)
        }

        loginUiState = loginUiState.copy(
            errorMessage = null,
            isValidate = true,
            isPadding = false
        )

        if (loginUiState.inputState.isRememberMe) {
            sharedPreferences.edit()
                .putString("username", loginUiState.inputState.username)
                .putString("password", loginUiState.inputState.password)
                .apply()
        }
    }
}

data class LoginInputsState(
    val username: String = "",
    val password: String = "",
    val isRememberMe: Boolean = false,
)

fun LoginInputsState.isValid(): Boolean {
    if (username.isEmpty()) return false
    if (password.isEmpty()) return false
    return true
}

fun LoginInputsState.toLoginError(): List<LoginError>? {
    val errors = mutableListOf<LoginError>()
    if (username.isEmpty()) errors.add(LoginError.EMPTY_USERNAME)
    if (password.isEmpty()) errors.add(LoginError.EMPTY_PASSWORD)
    if (errors.isNotEmpty()) return errors
    return null
}

data class LoginUiState(
    val inputState: LoginInputsState = LoginInputsState(),
    val isValidate: Boolean = false,
    val errorMessage: List<LoginError>? = null,
    val isPadding: Boolean = false,
)

enum class LoginError(val message: String) {
    EMPTY_USERNAME("Username is required"),
    EMPTY_PASSWORD("Password is required"),
    CREDENTIAL_INVALID("Username or password is invalid"),
}