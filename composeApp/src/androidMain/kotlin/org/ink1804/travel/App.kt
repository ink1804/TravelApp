package org.ink1804.travel

import android.app.Application
import com.ink1804.di.initKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}