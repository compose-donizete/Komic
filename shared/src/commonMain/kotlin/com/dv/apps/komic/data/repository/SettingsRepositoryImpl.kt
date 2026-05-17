package com.dv.apps.komic.data.repository

import com.dv.apps.komic.data.room.settings.folder.FolderDao
import com.dv.apps.komic.data.room.settings.folder.FolderEntity
import com.dv.apps.komic.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val folderDao: FolderDao
) : SettingsRepository {
    override suspend fun addFolder(path: String) {
        val entity = FolderEntity(
            id = 0,
            path
        )
        folderDao.insert(entity)
    }

    override fun getFolders() = folderDao.get().map {
        it.map(FolderEntity::path)
    }

    override suspend fun deleteFolder(path: String) {
        folderDao.delete(path)
    }
}