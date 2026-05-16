package com.dv.apps.komic.feature.settings.folder

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class State(
    val isLoading: Boolean = false,
    val noSelection: Boolean = false,
    val selectedFolders: List<String> = emptyList()
) {
    fun copyWithSelectedFolders(
        selectedFolders: List<String>
    ) = copy(selectedFolders = selectedFolders)
}

sealed interface Intent {
    data class OnFileTreeSelected(val path: String?) : Intent
}

class FolderSourceSettingsSectionViewModel : ViewModel() {
    val state = MutableStateFlow(State())

    fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnFileTreeSelected -> onFileTreeSelected(intent.path)
        }
    }

    private fun onFileTreeSelected(path: String?) {
        if (path == null) {
            state.update { it.copy(noSelection = true) }
            return
        }
    }
}