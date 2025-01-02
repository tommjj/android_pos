package com.android.pos.ui.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.pos.R
import com.android.pos.ui.AppViewModelProvider
import com.android.pos.ui.navigation.NavigationDestination

@Composable
fun LoginScreen(
    navigateToSignUpScreen: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold() { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Button(
                onClick = {
                    Log.d("LoginScreen", "Login")
                },
            ) {
                Text("Login")
            }
            Button(
                onClick = {
                    navigateToSignUpScreen()
                },
            ) {
                Text("Sign Up")
            }
        }

    }

}

object LoginDestination : NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.login_title
}