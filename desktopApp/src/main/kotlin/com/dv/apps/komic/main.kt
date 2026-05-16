package com.dv.apps.komic

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dv.apps.komic.di.mainModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(mainModule)
    }
    Window(
        title = "Komic",
        onCloseRequest = ::exitApplication,
        alwaysOnTop = true
    ) {
        App(window)
    }
}