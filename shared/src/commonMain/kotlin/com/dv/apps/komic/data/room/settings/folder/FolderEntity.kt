package com.dv.apps.komic.data.room.settings.folder

import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "folders",
    indices = [
        Index(value = ["path"], unique = true),
    ]
)
class FolderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val path: String
)