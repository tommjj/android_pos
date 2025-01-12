package com.android.pos.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.pos.R
import com.android.pos.ui.AppViewModelProvider
import com.android.pos.ui.navigation.NavigationDestination
import com.android.pos.ui.navigation.RouteGroups

object ProfileHomeDestination : NavigationDestination {
    override val route = "Profile"
    override val titleRes = R.string.profile_home_title
    override val routeGroup = RouteGroups.YOU
}

@Composable
fun ProfileHomeScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    viewModel: ProfileHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = viewModel.getUserName(),
                fontSize = 42.sp
            )

            OutlinedButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    viewModel.logout()
                    navigateToLogin()
                }) {
                Text(text = "Logout", fontSize = 24.sp, fontWeight = FontWeight.Normal)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileHomeScreenPreview() {
    ProfileHomeScreen(navigateToLogin = {})
}