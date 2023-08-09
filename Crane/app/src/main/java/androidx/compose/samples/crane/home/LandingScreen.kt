package androidx.compose.samples.crane.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.samples.crane.R
import androidx.compose.samples.crane.ui.theme.CraneTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

private const val SPLASH_WAIT_TIME: Long = 2000

@Composable
fun LandingScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .semantics { contentDescription = "LandingScreen" },
        contentAlignment = Alignment.Center,
    ) {

        val currentOnTimeout by rememberUpdatedState(newValue = onTimeout)

        LaunchedEffect(key1 = Unit) {
            delay(SPLASH_WAIT_TIME)
            currentOnTimeout()
        }

        Image(
            painter = painterResource(id = R.drawable.ic_crane_drawer),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
fun LandingScreenPreview(
    modifier: Modifier = Modifier,
    onTimeout: () -> Unit = {},
) {
    CraneTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            LandingScreen(onTimeout = onTimeout)
        }
    }
}



