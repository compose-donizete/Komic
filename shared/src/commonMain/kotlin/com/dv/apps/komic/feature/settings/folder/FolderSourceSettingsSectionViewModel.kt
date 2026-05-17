package com.dv.apps.komic.feature.settings.folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dv.apps.komic.domain.model.FolderSettings
import com.dv.apps.komic.domain.repository.FolderSettingsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class State(
    val isLoading: Boolean = false,
    val folderSettings: FolderSettings = FolderSettings()
)

sealed interface Intent {
    data class OnFileTreeSelected(val path: String?) : Intent
}

class FolderSourceSettingsSectionViewModel(
    private val folderSettingsRepository: FolderSettingsRepository
) : ViewModel() {
    private val isLoading = MutableStateFlow(0)

    val state = combine(
        isLoading.map { it > 0 },
        folderSettingsRepository.get(),
        ::State
    ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State())

    fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnFileTreeSelected -> onFileTreeSelected(intent.path ?: return)
        }
    }

    private fun onFileTreeSelected(path: String) {
        launch {
            folderSettingsRepository.add(path)
        }
    }

    private fun launch(block: suspend () -> Unit) {
        isLoading.update { it + 1 }
        viewModelScope.launch { block() }.invokeOnCompletion {
            isLoading.update { it - 1 }
        }
    }
}