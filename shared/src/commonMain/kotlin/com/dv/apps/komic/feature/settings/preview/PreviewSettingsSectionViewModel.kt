package com.dv.apps.komic.feature.settings.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dv.apps.komic.domain.model.PreviewSettings
import com.dv.apps.komic.domain.repository.PreviewSettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class State(
    val previewSettings: PreviewSettings = PreviewSettings(4, 4, PreviewSettings.Quality.HD)
)

sealed interface Intent {
    data class OnVerticalCountChanged(val size: Int) : Intent
    data class OnHorizontalCountChanged(val size: Int) : Intent
    data class OnQualityChanged(val quality: PreviewSettings.Quality) : Intent
}

class PreviewSettingsSectionViewModel(
    private val previewSettingsRepository: PreviewSettingsRepository
) : ViewModel() {
    val state = previewSettingsRepository.get().map(
        ::State
    ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State())

    fun handleIntent(intent: Intent) = viewModelScope.launch {
        when (intent) {
            is Intent.OnVerticalCountChanged if (intent.size > 0)  -> {
                previewSettingsRepository.set(state.value.previewSettings.copy(verticalCount = intent.size))
            }

            is Intent.OnHorizontalCountChanged if (intent.size > 0) -> {
                previewSettingsRepository.set(state.value.previewSettings.copy(horizontalCount = intent.size))
            }

            is Intent.OnQualityChanged -> {
                previewSettingsRepository.set(state.value.previewSettings.copy(quality = intent.quality))
            }

            else -> Unit
        }
    }
}