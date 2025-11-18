package com.ink1804.core.database

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databasePlatformModule: Module = module {
    single { DatabaseDriverFactory(androidContext()) }
}
