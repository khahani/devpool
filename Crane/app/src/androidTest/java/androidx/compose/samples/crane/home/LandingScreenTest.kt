package androidx.compose.samples.crane.home

import androidx.compose.samples.crane.ui.theme.CraneTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class LandingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun launchingLandingScreen() {
        composeTestRule.setContent {
            CraneTheme {
                MainScreen()
            }
        }

        composeTestRule
            .onNodeWithContentDescription(LANDING_SCREEN)
            .assertIsDisplayed()
    }
}
