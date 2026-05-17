package com.dv.apps.komic.data.room.settings.preview

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(
    tableName = "preview",
)
class PreviewSettingsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val verticalCount: Int,
    val horizontalCount: Int,
    val quality: Int,
)