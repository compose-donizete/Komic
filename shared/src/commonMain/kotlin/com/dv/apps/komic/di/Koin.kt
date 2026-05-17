package com.dv.apps.komic.di

import com.dv.apps.komic.data.repository.FolderSettingsRepositoryImpl
import com.dv.apps.komic.data.repository.PreviewSettingsRepositoryImpl
import com.dv.apps.komic.data.room.AppDatabase
import com.dv.apps.komic.data.room.AppDatabaseBuilder
import com.dv.apps.komic.domain.repository.FolderSettingsRepository
import com.dv.apps.komic.domain.repository.PreviewSettingsRepository
import com.dv.apps.komic.feature.settings.folder.FolderSourceSettingsSectionViewModel
import com.dv.apps.komic.feature.settings.preview.PreviewSettingsSectionViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single
import org.koin.plugin.module.dsl.viewModel

expect val platformModule: Module

val roomModule = module {
    single<AppDatabaseBuilder>()
    singleOf(AppDatabaseBuilder::invoke)
    singleOf(AppDatabase::folderSettingsDao)
    singleOf(AppDatabase::previewSettingsDao)
}

val repositoryModule = module {
    single<FolderSettingsRepositoryImpl>() bind FolderSettingsRepository::class
    single<PreviewSettingsRepositoryImpl>() bind PreviewSettingsRepository::class
}

val viewModelModule = module {
    viewModel<FolderSourceSettingsSectionViewModel>()
    viewModel<PreviewSettingsSectionViewModel>()
}

val mainModule = module {
    includes(platformModule, viewModelModule, roomModule, repositoryModule)
}