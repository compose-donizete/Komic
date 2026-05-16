package com.dv.apps.komic

import android.app.Application
import com.dv.apps.komic.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(mainModule)
            androidContext(this@MainApplication)
            androidLogger()
        }
    }
}