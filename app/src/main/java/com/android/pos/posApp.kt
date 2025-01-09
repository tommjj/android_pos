package com.android.pos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.pos.ui.home.HomeDestination
import com.android.pos.ui.navigation.AppNavGraph
import com.android.pos.ui.navigation.BottomNav
import com.android.pos.ui.navigation.NavigationDestination

val bottomNavDestinations = mapOf<String, NavigationDestination>(
    HomeDestination.route to HomeDestination
)

@Composable
fun POSApp(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            val currentDestinations: NavigationDestination? =
                bottomNavDestinations[navController.currentBackStackEntryAsState().value?.destination?.route]

            if (currentDestinations != null) {
                BottomNav(
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        AppNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}