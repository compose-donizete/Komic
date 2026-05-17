package com.dv.apps.komic.domain.repository

import com.dv.apps.komic.domain.model.PreviewSettings
import kotlinx.coroutines.flow.Flow

interface PreviewSettingsRepository {
    suspend fun set(o: PreviewSettings)
    fun get(): Flow<PreviewSettings>
}