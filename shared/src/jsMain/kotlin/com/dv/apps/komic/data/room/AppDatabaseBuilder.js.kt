package com.dv.apps.komic.data.room

import androidx.room3.Room
import androidx.sqlite.driver.web.WebWorkerSQLiteDriver
import org.w3c.dom.Worker

private fun getWorker(): Worker = js("""new Worker(new URL("sqlite-web-worker/worker.js", import.meta.url))""")

actual class AppDatabaseBuilder {
    actual operator fun invoke(): AppDatabase {
        return Room.databaseBuilder(
            "komic.db",
            AppDatabaseConstructor::initialize
        )
            .setDriver(WebWorkerSQLiteDriver(getWorker()))
            .fallbackToDestructiveMigration(true)
            .fallbackToDestructiveMigrationOnDowngrade(true)
            .fallbackToDestructiveMigrationFrom(true, 1)
            .build()
    }
}