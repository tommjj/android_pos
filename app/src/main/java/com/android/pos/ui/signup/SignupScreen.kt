package com.android.pos.ui.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.pos.R
import com.android.pos.ui.AppViewModelProvider
import com.android.pos.ui.components.PasswordTextField
import com.android.pos.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navigateToLoginScreen: () -> Unit,
    viewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold() { innerPadding ->
        CreateAccountBody(
            modifier = Modifier.padding(innerPadding),
            navigateToLoginScreen = navigateToLoginScreen,
            onCreate = {
                coroutineScope.launch {
                    try {
                        viewModel.onConfirm()
                        Toast.makeText(context ,"Sign up success", Toast.LENGTH_SHORT).show()
                        navigateToLoginScreen()
                    } catch (err: Exception) {
                        Log.e("SignUpScreen", "onCreate: ${err.message}")
                        Log.d("SignUpScreen", "onCreate: ${viewModel.signUpState.errorMessage}")
                    }
                }
            },
            onValueChange = {
                viewModel.updateUiState(it)
            },
            uiState = viewModel.signUpState
        )
    }
}

object SignUpDestination : NavigationDestination {
    override val route = "SignUp"
    override val titleRes = R.string.signup_title
}

@Composable
fun CreateAccountBody(
    navigateToLoginScreen: () -> Unit,
    onCreate: () -> Unit,
    modifier: Modifier = Modifier,
    uiState: SignUpUiState = SignUpUiState(),
    onValueChange: (SignUpInputState) -> Unit = {},
) {
    val usernameErrMess : String = remember(uiState.errorMessage) {
        when {
            uiState.errorMessage?.contains(SignUpError.USER_EXIST) == true -> SignUpError.USER_EXIST.message
            uiState.errorMessage?.contains(SignUpError.USER_NAME_INVALID) == true -> SignUpError.USER_NAME_INVALID.message
            else -> ""
        }
    }

    val passwordErrMess : String = remember (uiState.errorMessage) {
        when {
            uiState.errorMessage?.contains(SignUpError.EMPTY_PASSWORD) == true -> SignUpError.EMPTY_PASSWORD.message
            uiState.errorMessage?.contains(SignUpError.PASSWORD_LENGTH_INVALID) == true -> SignUpError.PASSWORD_LENGTH_INVALID.message
            else -> ""
        }
    }

    val confirmPasswordErrMess : String = remember (uiState.errorMessage) {
        when {
            uiState.errorMessage?.contains(SignUpError.EMPTY_CONFIRM_PASSWORD) == true -> SignUpError.EMPTY_CONFIRM_PASSWORD.message
            uiState.errorMessage?.contains(SignUpError.PASSWORD_MISMATCH) == true -> SignUpError.PASSWORD_MISMATCH.message
            else -> ""
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .height(IntrinsicSize.Min)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Icon(
            painter = painterResource(id = R.drawable.package_2),
            contentDescription = "Logo Icon",
            modifier = Modifier.size(50.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .width(5.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxHeight(0.8f)
            )
            Text(
                text = "Create \nan account",
                fontSize = 45.sp,
                lineHeight = 47.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = uiState.inputState.username,
            onValueChange = { onValueChange(uiState.inputState.copy(username = it)) },
            label = { Text("Username") },
            modifier =  Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            shape = Shapes().small
        )
        Text(
            usernameErrMess,
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            modifier = Modifier
                .height(16.dp)
        )

        PasswordTextField(
            value = uiState.inputState.password,
            onValueChange = { onValueChange(uiState.inputState.copy(password = it)) },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            shape = Shapes().small,
        )
        Text(
            passwordErrMess,
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            modifier = Modifier
                .height(16.dp)
        )
        PasswordTextField(
            value = uiState.inputState.confirmPassword,
            onValueChange = { onValueChange(uiState.inputState.copy(confirmPassword = it)) },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            shape = Shapes().small,
        )
        Text(
            confirmPasswordErrMess,
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            modifier = Modifier
                .height(26.dp)
        )
        Button(
            onClick = {
                onCreate()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = Shapes().small,
        ) {
            Text("Sign Up")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                "Already have an account? "
            )
            Text(
                text = "Sign In",
                modifier = Modifier.clickable {
                    navigateToLoginScreen()
                },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CreateAccountScreenPreview() {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            CreateAccountBody(navigateToLoginScreen = {},
                onCreate = {})
        }
    }
}