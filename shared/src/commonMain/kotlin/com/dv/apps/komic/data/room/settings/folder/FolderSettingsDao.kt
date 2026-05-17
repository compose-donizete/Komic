package com.dv.apps.komic.data.room.settings.folder

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderSettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FolderSettingsEntity)

    @Query("SELECT * FROM folder")
    fun get(): Flow<List<FolderSettingsEntity>>

    @Query("DELETE FROM folder WHERE path = :path")
    suspend fun delete(path: String)
}