package com.dv.apps.komic.data.repository

import com.dv.apps.komic.data.room.settings.preview.PreviewSettingsDao
import com.dv.apps.komic.data.room.settings.preview.PreviewSettingsEntity
import com.dv.apps.komic.domain.model.PreviewSettings
import com.dv.apps.komic.domain.repository.PreviewSettingsRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class PreviewSettingsRepositoryImpl(
    private val previewSettingsDao: PreviewSettingsDao
) : PreviewSettingsRepository {
    override suspend fun set(o: PreviewSettings) {
        previewSettingsDao.insert(o.run {
            PreviewSettingsEntity(
                id = 0,
                verticalCount,
                horizontalCount,
                quality.ordinal
            )
        })
    }

    override fun get() = previewSettingsDao.get().filterNotNull().map {
        with(it) {
            PreviewSettings(
                verticalCount,
                horizontalCount,
                PreviewSettings.Quality.entries.getOrElse(quality) { PreviewSettings.Quality.HD }
            )
        }
    }
}