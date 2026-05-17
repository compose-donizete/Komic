package com.dv.apps.komic.data.repository

import com.dv.apps.komic.data.room.settings.folder.FolderDao
import com.dv.apps.komic.data.room.settings.folder.FolderEntity
import com.dv.apps.komic.domain.repository.FolderRepository
import kotlinx.coroutines.flow.map

class FolderRepositoryImpl(
    private val folderDao: FolderDao
) : FolderRepository {
    override suspend fun add(path: String) {
        val entity = FolderEntity(
            id = 0,
            path
        )
        folderDao.insert(entity)
    }

    override fun get() = folderDao.get().map {
        it.map(FolderEntity::path)
    }

    override suspend fun delete(path: String) {
        folderDao.delete(path)
    }
}