package com.dv.apps.komic.data.room

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import com.dv.apps.komic.data.room.settings.folder.FolderDao
import com.dv.apps.komic.data.room.settings.folder.FolderEntity

@Database(
    exportSchema = false,
    entities = [
        FolderEntity::class
    ],
    version = 1,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val folderDao: FolderDao
}

expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}