package com.android.pos

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.pos.ui.navigation.AppNavGraph

@Composable
fun POSApp(navController: NavHostController = rememberNavController()) {
    AppNavGraph(navController = navController)
}