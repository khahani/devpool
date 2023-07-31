package androidx.compose.samples.crane.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val crane_red = Color(0xFFE30425)
private val crane_white = Color.White
private val crane_purple_700 = Color(0xFF720D5D)
private val crane_purple_800 = Color(0xFF5D1049)
private val crane_purple_900 = Color(0xFF4E0D3A)

val craneColors = lightColorScheme(
    primary = crane_purple_800,
    tertiary = crane_red,
    surface = crane_purple_900,
    onSurface = crane_white,
    secondary = crane_purple_700,
)
