package com.ink1804.infra.sqldelight

import org.koin.core.module.Module
import org.koin.dsl.module

actual val databasePlatformModule: Module = module {
    single { DatabaseDriverFactory() }
}
