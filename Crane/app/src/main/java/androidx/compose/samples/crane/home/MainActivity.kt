package androidx.compose.samples.crane.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.samples.crane.base.CraneDrawer
import androidx.compose.samples.crane.ui.theme.CraneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CraneTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val showLandingScreen = remember {
        mutableStateOf(true)
    }
    if (showLandingScreen.value) {
        LandingScreen(onTimeout = { showLandingScreen.value = false })
    } else {
        CraneDrawer()
    }
}
