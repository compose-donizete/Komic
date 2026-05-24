package com.dv.apps.komic

import androidx.compose.runtime.Composable

actual object Window

@Composable
actual fun Window.registerFolderPicker(): suspend () -> String? = {
    null
}