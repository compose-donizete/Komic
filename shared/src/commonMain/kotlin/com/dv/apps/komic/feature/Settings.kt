package com.dv.apps.komic.feature

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Settings() {
    val vm = koinViewModel<SettingsViewModel>()
    val state by vm.helloWorld.collectAsStateWithLifecycle()
    Text(state)
}