package com.android.pos.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.pos.R
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
                text = "Profile Home Screen",
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileHomeScreenPreview() {
    ProfileHomeScreen()
}