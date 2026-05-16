package com.dv.apps.komic

import androidx.compose.runtime.Composable

expect class Window

@Composable
expect fun Window.registerFolderPicker(): suspend () -> String?