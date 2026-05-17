package com.dv.apps.komic.data.room.settings.preview

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PreviewSettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: PreviewSettingsEntity)

    @Query("SELECT * FROM preview")
    fun get(): Flow<PreviewSettingsEntity?>
}