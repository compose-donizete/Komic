package com.dv.apps.komic

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import kotlinx.coroutines.channels.Channel


actual typealias Window = android.app.Activity

@Composable
actual fun Window.registerFolderPicker(): suspend () -> String? {
    val res = Channel<String?>()
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) {
        res.trySend(it?.toString())
    }
    return {
        launcher.launch(null)
        res.receive()
    }
}