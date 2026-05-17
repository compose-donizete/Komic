package com.dv.apps.komic

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.dv.apps.komic.di.mainModule
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(mainModule)
    }
    ComposeViewport {
        App(Unit)
    }
}