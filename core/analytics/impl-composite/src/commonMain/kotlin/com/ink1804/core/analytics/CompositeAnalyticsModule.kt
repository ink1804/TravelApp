package com.ink1804.core.analytics

import org.koin.dsl.module

val compositeAnalyticsModule = module {
    includes(debugAnalyticsModule)

    single<Analytics> {
        CompositeAnalyticsImpl(getAll<AnalyticsDelegate>())
    }
}
