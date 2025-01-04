package com.android.pos.ui.login

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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


object LoginDestination : NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.login_title
}

@Composable
fun LoginScreen(
    navigateToSignUpScreen: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold() { innerPadding ->
        LoginBody(
            navigateToSignUpScreen = navigateToSignUpScreen,
            modifier = Modifier.padding(innerPadding),
            onSubmit = {
                coroutineScope.launch {
                    try {
                        viewModel.submit()
                        Toast.makeText(context ,"Login success", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Log.e("LoginScreen", e.message ?: "Unknown error")
                    }
                }
            },
            onValueChange = {
                viewModel.onInputChanged(it)
            },
            uiState = viewModel.loginUiState
        )
    }
}

@Composable
fun LoginBody(
    navigateToSignUpScreen: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    uiState: LoginUiState = LoginUiState(),
    onValueChange: (LoginInputsState) -> Unit = {},
) {
    val usernameErrorMessages = remember(uiState.errorMessage) {
        when {
            uiState.errorMessage?.contains(LoginError.EMPTY_USERNAME) == true ->
                LoginError.EMPTY_USERNAME.message
            uiState.errorMessage?.contains(LoginError.CREDENTIAL_INVALID) == true -> LoginError.CREDENTIAL_INVALID.message
            else -> ""
        }
    }

    val passwordErrorMessages = remember(uiState.errorMessage) {
        when {
            uiState.errorMessage?.contains(LoginError.EMPTY_PASSWORD) == true ->
                LoginError.EMPTY_PASSWORD.message
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
                text = "Login \nyour account",
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
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            shape = Shapes().small
        )
        Text(
            usernameErrorMessages,
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
            passwordErrorMessages,
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            modifier = Modifier
                .height(16.dp)
        )
        Button(
            onClick = {
                onSubmit()
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
                "Don't have an account? "
            )
            Text(
                text = "Sign Up",
                modifier = Modifier.clickable {
                    navigateToSignUpScreen()
                },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Scaffold() { innerPadding ->
        LoginBody(
            navigateToSignUpScreen = { },
            modifier = Modifier.padding(innerPadding),
            onSubmit = {
            },
            onValueChange = {},
        )
    }
}

