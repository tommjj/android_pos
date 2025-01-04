package com.android.pos.ui.signup

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.android.pos.data.User
import com.android.pos.data.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    var signUpState by mutableStateOf(SignUpUiState())
        private set

    fun updateUiState(itemDetails: SignUpInputState) {
        signUpState = SignUpUiState(
            inputState = itemDetails,
            isValidate = itemDetails.isValid(),
        )
    }

    suspend fun onConfirm() {
        if (signUpState.isPadding) return
        signUpState = signUpState.copy(isPadding = true)

        val errors = signUpState.inputState.toSignUpError()
        if (errors != null) {
            signUpState = signUpState.copy(errorMessage = errors, isPadding = false)
            throw Exception("Invalid input")
        }

        val user = userRepository.getUserStream(signUpState.inputState.username).firstOrNull()
        if (user != null) {
            signUpState = signUpState.copy(errorMessage = listOf(SignUpError.USER_EXIST), isPadding = false)
            throw Exception("User exist")
        }

        userRepository.insertUser(signUpState.inputState.toUser())

        signUpState = signUpState.copy(isPadding = false)
    }
}

data class SignUpUiState(
    val inputState: SignUpInputState = SignUpInputState(),
    val isValidate: Boolean = false,
    val errorMessage: List<SignUpError>? = null,
    val isPadding: Boolean = false,
)

data class SignUpInputState(
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)

enum class SignUpError(val message: String) {
    USER_EXIST("User exist"),
    USER_NAME_INVALID("User name invalid"),
    EMPTY_USERNAME("Username is required"),
    EMPTY_PASSWORD("Password is required"),
    PASSWORD_LENGTH_INVALID("Password must contain at least 7 characters"),
    EMPTY_CONFIRM_PASSWORD("Confirm password is empty"),
    PASSWORD_MISMATCH("Password mismatch"),
}

fun SignUpInputState.isValid(): Boolean {
    if (username.isEmpty()) return false
    if (password.isEmpty()) return false
    if (confirmPassword.isEmpty()) return false
    if (password != confirmPassword) return false
    return true
}

fun SignUpInputState.toSignUpError(): List<SignUpError>? {
    val regex = Regex("^[a-zA-Z0-9_]{3,}\$")

    val errors = mutableListOf<SignUpError>()
    if (username.isEmpty()) errors.add(SignUpError.EMPTY_USERNAME)
    if (username.length < 3) errors.add(SignUpError.USER_NAME_INVALID)
    if (!regex.matches(username)) errors.add(SignUpError.USER_NAME_INVALID)

    if (password.isEmpty()) errors.add(SignUpError.EMPTY_PASSWORD)
    if (confirmPassword.isEmpty()) errors.add(SignUpError.EMPTY_CONFIRM_PASSWORD)
    if (password != confirmPassword) errors.add(SignUpError.PASSWORD_MISMATCH)
    if (password.length < 7) errors.add(SignUpError.PASSWORD_LENGTH_INVALID)

    if (errors.isNotEmpty()) return errors
    return null
}

fun SignUpInputState.toUser() = User(
    name = username,
    password = password,
)