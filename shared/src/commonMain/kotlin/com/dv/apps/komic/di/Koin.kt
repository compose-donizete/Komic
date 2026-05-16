package com.dv.apps.komic.di

import com.dv.apps.komic.feature.SettingsViewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

expect val platformModule: Module

val mainModule = module {
    includes(platformModule)
    viewModel<SettingsViewModel>()
}