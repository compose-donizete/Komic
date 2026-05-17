package com.dv.apps.komic.domain.repository

import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    suspend fun add(path: String)
    fun get(): Flow<List<String>>
    suspend fun delete(path: String)
}