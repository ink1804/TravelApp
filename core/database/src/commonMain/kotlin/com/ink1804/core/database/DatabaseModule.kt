package com.ink1804.core.database

import org.koin.core.module.Module
import org.koin.dsl.module

expect val databasePlatformModule: Module

val databaseModule = module {
    includes(databasePlatformModule)
    single { createDatabase(get()) }
}
