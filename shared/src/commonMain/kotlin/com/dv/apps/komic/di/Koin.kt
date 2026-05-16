package com.dv.apps.komic.di

import com.dv.apps.komic.feature.settings.folder.FolderSourceSettingsSectionViewModel
import com.dv.apps.komic.feature.settings.preview.PreviewSettingsSectionViewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

expect val platformModule: Module

val viewModelModule = module {
    viewModel<FolderSourceSettingsSectionViewModel>()
    viewModel<PreviewSettingsSectionViewModel>()
}

val mainModule = module {
    includes(platformModule, viewModelModule)
}