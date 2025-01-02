package com.android.pos.ui.signup

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.pos.R
import com.android.pos.ui.AppViewModelProvider
import com.android.pos.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navigateToLoginScreen: () -> Unit,
    viewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold() {
            innerPadding ->
        Button(
            modifier = Modifier.padding(innerPadding),
            onClick = {
                coroutineScope.launch {
                    Log.d("SignUpScreen", "create new acc")
                    viewModel.createAccount_test()
                    Log.d("SignUpScreen", "created ")
                    navigateToLoginScreen()
                }
            },
        ) {
            Text("Create new acc")
        }
    }
}

object SignUpDestination : NavigationDestination {
    override val route = "SignUp"
    override val titleRes = R.string.signup_title
}