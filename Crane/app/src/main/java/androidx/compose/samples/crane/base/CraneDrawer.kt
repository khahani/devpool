package androidx.compose.samples.crane.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.samples.crane.R
import androidx.compose.samples.crane.home.CRANE_DRAWER
import androidx.compose.samples.crane.ui.theme.CraneTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

private val screens = listOf(
    "Find Trips",
    "My Trips",
    "Saved Trips",
    "Price Alerts",
    "My Account",
)

@Composable
fun CraneDrawer() {
    val selectedItem = remember {
        mutableStateOf(screens[0])
    }
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
    )
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                screens.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(text = item) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            selectedItem.value = item
                        },
                        modifier = Modifier
                            .padding(
                                paddingValues = NavigationDrawerItemDefaults.ItemPadding,
                            ),
                    )
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_menu),
                                contentDescription = stringResource(id = R.string.cd_drawer),
                                modifier = Modifier.clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                            )
                        }
                    }
                },
            ) {
                it
            }
        },
        modifier = Modifier
            .semantics { contentDescription = CRANE_DRAWER },
    )
}

@Preview
@Composable
fun CraneDrawerPreview() {
    CraneTheme {
        Surface(
            color = MaterialTheme.colorScheme.primary,
        ) {
            CraneDrawer()
        }
    }
}
