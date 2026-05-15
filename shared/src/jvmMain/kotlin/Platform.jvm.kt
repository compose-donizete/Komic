package com.dv.apps.komic

import androidx.compose.runtime.Composable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.swing.JFileChooser

actual typealias Window = androidx.compose.ui.awt.ComposeWindow

@Composable
actual fun Window.registerFolderPicker(): suspend () -> String? = {
    val fc = JFileChooser().apply {
        fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    }
    withContext(Dispatchers.IO) {
        val ret = fc.showOpenDialog(this@registerFolderPicker)
        val file = when (ret) {
            JFileChooser.APPROVE_OPTION -> fc.selectedFile
            else -> null
        }
        file?.absolutePath
    }
}