package com.dv.apps.komic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalFolderPicker = staticCompositionLocalOf<suspend () -> String?> {
    error("FolderPicker not initialized")
}

@Composable
fun App(window: Window) {
    val folderPicker = window.registerFolderPicker()

    KomicTheme {
        CompositionLocalProvider(
            LocalFolderPicker provides folderPicker
        ) {
            Navigation()
        }
    }
}