package com.dv.apps.komic.data.room

expect class AppDatabaseBuilder {
    operator fun invoke(): AppDatabase
}