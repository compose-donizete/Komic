package com.dv.apps.komic.feature.settings.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class State(
    val verticalPreviewSpanSize: Int = 0,
    val horizontalPreviewSpanSize: Int = 0,
    val quality: Settings.Quality = Settings.Quality.HD
)

sealed interface Intent {
    data class OnVerticalPreviewSpanSizeChanged(val size: Int) : Intent
    data class OnHorizontalPreviewSpanSizeChanged(val size: Int) : Intent
    data class OnQualityChanged(val quality: Settings.Quality) : Intent
}

object Settings {
    enum class Quality {
        HD,
        FULL_HD,
        TWO_K,
        FOUR_K
    }
}

class PreviewSettingsSectionViewModel : ViewModel() {
    val state = MutableStateFlow(State())

    fun handleIntent(intent: Intent) = viewModelScope.launch {
        when (intent) {
            is Intent.OnVerticalPreviewSpanSizeChanged -> {

            }

            is Intent.OnHorizontalPreviewSpanSizeChanged -> {

            }

            is Intent.OnQualityChanged -> {

            }
        }
    }
}