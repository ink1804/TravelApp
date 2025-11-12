package com.ink1804.core.platform

import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    factory<ShakeDetector> { ShakeDetector() }
}