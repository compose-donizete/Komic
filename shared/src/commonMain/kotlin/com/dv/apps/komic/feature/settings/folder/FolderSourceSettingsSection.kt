package com.dv.apps.komic.feature.settings.folder

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dv.apps.komic.KomicTheme
import com.dv.apps.komic.LocalFolderPicker
import com.dv.apps.komic.feature.settings.SettingsSection
import komic.shared.generated.resources.Res
import komic.shared.generated.resources.ic_folder_add
import komic.shared.generated.resources.settings_section_selected_folders_title
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FolderSourceSettingsSection() {
    if (LocalInspectionMode.current) {
        FolderSourceSettingsSection(State())
    } else {
        val vm = koinViewModel<FolderSourceSettingsSectionViewModel>()
        val state by vm.state.collectAsStateWithLifecycle()
        FolderSourceSettingsSection(state, vm::handleIntent)
    }
}

@Composable
fun FolderSourceSettingsSection(
    state: State,
    dispatchIntent: (Intent) -> Unit = {}
) {
    val co = rememberCoroutineScope()
    val folderPicker = LocalFolderPicker.current

    Column {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(Res.string.settings_section_selected_folders_title),
                style = MaterialTheme.typography.titleMedium
            )

            IconButton(
                onClick = {
                    co.launch {
                        val folder = folderPicker()
                        dispatchIntent(Intent.OnFileTreeSelected(folder))
                    }
                }
            ) {
                Icon(
                    painterResource(Res.drawable.ic_folder_add),
                    contentDescription = ""
                )
            }
        }

        if (state.selectedFolders.isNotEmpty()) {
            HorizontalDivider(
                Modifier.padding(horizontal = 8.dp)
            )

            Column(Modifier.padding(vertical = 8.dp)) {
                for (folder in state.selectedFolders) {
                    Text(folder)
                }
            }
        }
    }
}

@Preview
@Composable
private fun FolderScreenPreview1() {
    KomicTheme {
        SettingsSection {
            FolderSourceSettingsSection(
                state = State(
                    selectedFolders = List(4) {
                        "/root/sdcard:folder/$it"
                    }
                )
            )
        }
    }
}

@Preview
@Composable
private fun FolderScreenPreview2() {
    KomicTheme {
        SettingsSection {
            FolderSourceSettingsSection(
                state = State(
                    selectedFolders = emptyList()
                )
            )
        }
    }
}