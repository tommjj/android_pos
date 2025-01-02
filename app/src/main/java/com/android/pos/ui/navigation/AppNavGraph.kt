package com.android.pos.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.pos.ui.login.LoginDestination
import com.android.pos.ui.login.LoginScreen
import com.android.pos.ui.signup.SignUpDestination
import com.android.pos.ui.signup.SignUpScreen


@Composable
fun AppNavGraph(
    navController: NavHostController =  rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = LoginDestination.route,
        enterTransition = {
            // you can change whatever you want transition
            EnterTransition.None
        },
        exitTransition = {
            // you can change whatever you want transition
            ExitTransition.None
        }
    ) {
        composable(route = LoginDestination.route) {
            LoginScreen(
                navigateToSignUpScreen = {
                    navController.navigate(SignUpDestination.route)
                }
            )
        }

        composable(route = SignUpDestination.route) {
            SignUpScreen(
                navigateToLoginScreen = {
                    navController.navigate(LoginDestination.route)
                }
            )
        }
    }
}