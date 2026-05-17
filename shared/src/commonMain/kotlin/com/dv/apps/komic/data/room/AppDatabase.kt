package com.dv.apps.komic.data.room

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import com.dv.apps.komic.data.room.settings.folder.FolderSettingsDao
import com.dv.apps.komic.data.room.settings.folder.FolderSettingsEntity
import com.dv.apps.komic.data.room.settings.preview.PreviewSettingsDao
import com.dv.apps.komic.data.room.settings.preview.PreviewSettingsEntity

@Database(
    exportSchema = false,
    entities = [
        FolderSettingsEntity::class,
        PreviewSettingsEntity::class,
    ],
    version = 1,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val folderSettingsDao: FolderSettingsDao
    abstract val previewSettingsDao: PreviewSettingsDao
}

expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}