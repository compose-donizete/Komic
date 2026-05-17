package com.dv.apps.komic.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun addFolder(path: String)
    fun getFolders(): Flow<List<String>>
    suspend fun deleteFolder(path: String)
}