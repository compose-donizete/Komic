package com.dv.apps.komic.data.room

import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

actual class AppDatabaseBuilder {
    actual operator fun invoke(): AppDatabase {
        val userHome = System.getProperty("user.home")
        val komicHome = File(userHome, ".komic")
        val file = File(komicHome, "komic.db")
        return Room.databaseBuilder(
            file.path,
            AppDatabaseConstructor::initialize
        )
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}