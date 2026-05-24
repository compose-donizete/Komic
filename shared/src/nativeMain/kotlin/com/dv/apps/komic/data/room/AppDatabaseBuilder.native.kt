package com.dv.apps.komic.data.room

import androidx.room3.Room
import androidx.sqlite.driver.NativeSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class AppDatabaseBuilder {
    actual operator fun invoke(): AppDatabase {
        val path = documentDirectory() + "/komic.db"
        return Room.databaseBuilder(
            name = path,
            AppDatabaseConstructor::initialize
        )
            .setDriver(NativeSQLiteDriver())
            .build()
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
}