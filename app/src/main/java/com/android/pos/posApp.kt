package com.android.pos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.pos.ui.home.HomeDestination
import com.android.pos.ui.navigation.AppNavGraph
import com.android.pos.ui.navigation.BottomNav
import com.android.pos.ui.navigation.NavigationDestination
import com.android.pos.ui.products.ProductHomeDestination
import com.android.pos.ui.profile.ProfileHomeDestination
import com.android.pos.ui.shop.ShopHomeDestination

val bottomNavDestinations = mapOf(
    HomeDestination.route to HomeDestination,
    ProductHomeDestination.route to ProductHomeDestination,
    ShopHomeDestination.route to ShopHomeDestination,
    ProfileHomeDestination.route to ProfileHomeDestination
)

@Composable
fun POSApp(navController: NavHostController = rememberNavController()) {
    val currentBackStack = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            val currentDestinations: NavigationDestination? =
                bottomNavDestinations[currentBackStack.value?.destination?.route]

            if (currentDestinations != null) {
                BottomNav(
                    navController = navController,
                    navDestinations = currentDestinations
                )
            }
        }
    ) { innerPadding ->
        AppNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}