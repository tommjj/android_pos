package com.android.pos.ui.shop

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

object ShopHomeDestination : NavigationDestination {
    override val route = "Shop"
    override val titleRes = R.string.shop_home_title
    override val routeGroup = RouteGroups.SHOP
}

@Composable
fun ShopHomeScreen(
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
                text = "Shop Home Screen",
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductHomeScreenPreview() {
    ShopHomeScreen()
}