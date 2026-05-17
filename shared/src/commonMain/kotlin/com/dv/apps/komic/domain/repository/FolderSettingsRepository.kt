package com.dv.apps.komic.domain.repository

import com.dv.apps.komic.domain.model.FolderSettings
import kotlinx.coroutines.flow.Flow

interface FolderSettingsRepository {
    suspend fun add(p: String)
    fun get(): Flow<FolderSettings>
    suspend fun delete(p: String)
}