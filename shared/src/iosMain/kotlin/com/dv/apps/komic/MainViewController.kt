package com.dv.apps.komic

import androidx.compose.ui.window.ComposeUIViewController
import com.dv.apps.komic.di.mainModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    App(Window)
}

fun initKoin() {
    startKoin {
        modules(mainModule)
    }
}