package com.dv.apps.komic.data.room

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver

actual class AppDatabaseBuilder(
    private val context: Context
) {
    actual operator fun invoke() = Room.databaseBuilder(
        context,
        context.getDatabasePath("komic.db").path,
        AppDatabaseConstructor::initialize
    )
        .setDriver(AndroidSQLiteDriver())
        .build()
}