package com.dv.apps.komic.feature

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel : ViewModel() {
    val helloWorld = MutableStateFlow("Hello World")
}