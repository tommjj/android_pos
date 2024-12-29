package com.android.pos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.pos.ui.theme.PosTheme
import com.android.pos.ui.theme.modifiers.noRippleClickable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://ak.gamepress.gg/sites/default/files/2022-09/unknown%5B1%5D.png")
                                .crossfade(true).build(),
                            placeholder = painterResource(R.drawable.ic_launcher_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GreetingPreview() {
    var count by remember { mutableIntStateOf(0) }

    PosTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text("count: $count")

                Spacer(Modifier.height(5.dp))

                Button(
                    onClick = {
                        count++
                    },
                ) {
                    Text("click me!")
                }

                Spacer(Modifier.height(5.dp))

                Text("click", Modifier.noRippleClickable {
                    count++
                })
            }
        }
    }
}