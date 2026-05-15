package com.dv.apps.komic

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import komic.shared.generated.resources.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation() {
    val state = rememberNavigationSuiteScaffoldState()
    val stack = mutableStateListOf(Destination.HOME)

    NavigationSuiteScaffold(
        state = state,
        navigationSuiteItems = {
            Destination.entries.forEach { destination ->
                item(
                    selected = stack.single() == destination,
                    onClick = { stack.fill(destination) },
                    icon = {
                        Icon(
                            painterResource(
                                if (stack.single() == destination) {
                                    destination.selectedIcon
                                } else {
                                    destination.icon
                                }
                            ),
                            contentDescription = stringResource(destination.title)
                        )
                    },
                    label = { Text(destination.name) }
                )
            }
        }
    ) {
        NavDisplay(
            stack,
            entryProvider = entryProvider {
                entry<Destination> {
                    when (it) {
                        Destination.HOME -> Text("HOME")
                        Destination.SHELF -> Text("SHELF")
                        Destination.SETTINGS -> Column {
                            val co = rememberCoroutineScope()
                            val folderPicker = LocalFolderPicker.current

                            Button(onClick = {
                                co.launch {
                                    val folder = folderPicker()
                                    println(folder)
                                }
                            }) {
                                Text("Click me")
                            }
                        }
                    }
                }
            }
        )
    }
}

enum class Destination(
    val title: StringResource,
    val icon: DrawableResource,
    val selectedIcon: DrawableResource
) {
    HOME(Res.string.menu_home, Res.drawable.ic_home, Res.drawable.ic_home_filled),
    SHELF(Res.string.menu_shelf, Res.drawable.ic_shelf, Res.drawable.ic_shelf_filled),
    SETTINGS(Res.string.menu_settings, Res.drawable.ic_settings, Res.drawable.ic_settings_filled)
}