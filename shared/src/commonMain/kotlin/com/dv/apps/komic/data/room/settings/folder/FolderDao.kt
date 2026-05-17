package com.dv.apps.komic.data.room.settings.folder

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FolderEntity)

    @Query("SELECT * FROM folders")
    fun get(): Flow<List<FolderEntity>>

    @Query("DELETE FROM folders WHERE path = :path")
    suspend fun delete(path: String)
}