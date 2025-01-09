package com.android.pos.ui.navigation

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.pos.R
import com.android.pos.ui.home.HomeDestination
import com.android.pos.ui.home.HomeScreen

fun dpFromPx(context: Context, px: Int): Float {
    return px / context.resources.displayMetrics.density
}

@Composable
fun BottomNav(
    navController: NavController,
    modifier: Modifier = Modifier
) {


    val buttonPadding = dpFromPx(
        context = LocalContext.current,
        px = WindowInsets.systemBars.getBottom(LocalDensity.current)
    )

    BottomAppBar(
        modifier = modifier.height((buttonPadding + 54).dp),
        contentPadding = PaddingValues(top = 0.dp, bottom = 0.dp),
    ) {
        Row(
            modifier = modifier.height(50.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                painter = painterResource(id = R.drawable.home),
                title = "Home"
            )
            BottomNavItem(
                painter = painterResource(id = R.drawable.shopping),
                title = "Shop"
            )
            BottomNavItem(
                painter = painterResource(id = R.drawable.inventory),
                title = "Products"
            )
            BottomNavItem(
                painter = painterResource(id = R.drawable.account_circle),
                title = "You"
            )
        }
    }
}

@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(50.dp)
            .width(90.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Icon(painter, contentDescription = "BottomNavIcon", modifier = Modifier.size(30.dp))
            Text(text = title, fontSize = 10.sp, lineHeight = 11.sp)
        }
    }
}


