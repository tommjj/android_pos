package com.android.pos.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.pos.R
import com.android.pos.ui.AppViewModelProvider
import com.android.pos.ui.navigation.NavigationDestination
import com.android.pos.ui.navigation.RouteGroups


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
    override val routeGroup = RouteGroups.HOME
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.height(56.dp).border(1.dp, color = Color.Red),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Home")
            }
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize().padding(top = innerPadding.calculateTopPadding()).border(1.dp, color = Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome ${viewModel.getUserName()}", fontSize = 32.sp)
        }
    }
}