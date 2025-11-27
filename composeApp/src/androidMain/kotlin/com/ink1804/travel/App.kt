package com.ink1804.travel

import android.app.Application
import com.ink1804.core.di.initKoin
import com.ink1804.core.firebase.FirebaseInitializer
import com.ink1804.travel.di.getAppModules
import org.koin.android.ext.koin.androidContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseInitializer.initialize(this)

        initKoin(
            modules = getAppModules(),
            config = {
                androidContext(this@App)
            }
        )
    }
}
