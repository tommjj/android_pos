package com.android.pos.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.android.pos.R
import com.android.pos.ui.home.HomeDestination
import com.android.pos.ui.home.HomeScreen
import com.android.pos.ui.login.LoginDestination
import com.android.pos.ui.login.LoginScreen
import com.android.pos.ui.products.ProductHomeDestination
import com.android.pos.ui.products.ProductHomeScreen
import com.android.pos.ui.profile.ProfileHomeDestination
import com.android.pos.ui.profile.ProfileHomeScreen
import com.android.pos.ui.shop.ShopHomeDestination
import com.android.pos.ui.shop.ShopHomeScreen
import com.android.pos.ui.signup.SignUpDestination
import com.android.pos.ui.signup.SignUpScreen

//object ItemEditDestination : NavigationDestination {
//    override val route = "item_edit"
//    override val titleRes = R.string.edit_item_title
//    const val itemIdArg = "itemId"
//    val routeWithArgs = "$route/{$itemIdArg}"
//}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = LoginDestination.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
    ) {
        composable(route = LoginDestination.route) {
            LoginScreen(
                navigateToSignUpScreen = {
                    navController.navigate(SignUpDestination.route)
                },
                navigateToHomeScreen = {
                    navController.navigate(AppDestination.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = SignUpDestination.route ) {
            SignUpScreen(
                navigateToLoginScreen = {
                    navController.navigate(LoginDestination.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        navigation(
            route = AppDestination.route,
            startDestination = HomeDestination.route,
        ) {
            composable(
                route = HomeDestination.route
            ) {
                HomeScreen()
            }

            composable(
                route = ProductHomeDestination.route
            ) {
                ProductHomeScreen()
            }

            composable(
                route = ShopHomeDestination.route
            ) {
                ShopHomeScreen()
            }

            composable(
                route = ProfileHomeDestination.route
            ) {
                ProfileHomeScreen()
            }
        }
    }
}

object AppDestination : NavigationDestination {
    override val route: String = "app"
    override val titleRes: Int = R.string.app_name
    override val routeGroup = null
}