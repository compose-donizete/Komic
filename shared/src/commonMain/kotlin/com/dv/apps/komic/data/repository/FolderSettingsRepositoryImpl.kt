package com.dv.apps.komic.data.repository

import com.dv.apps.komic.data.room.settings.folder.FolderSettingsDao
import com.dv.apps.komic.data.room.settings.folder.FolderSettingsEntity
import com.dv.apps.komic.domain.model.FolderSettings
import com.dv.apps.komic.domain.repository.FolderSettingsRepository
import kotlinx.coroutines.flow.map

class FolderSettingsRepositoryImpl(
    private val folderDao: FolderSettingsDao
) : FolderSettingsRepository {
    override suspend fun add(p: String) {
        val entity = FolderSettingsEntity(
            id = 0,
            p
        )
        folderDao.insert(entity)
    }

    override fun get() = folderDao.get().map {
        FolderSettings(it.map(FolderSettingsEntity::path))
    }

    override suspend fun delete(p: String) {
        folderDao.delete(p)
    }
}