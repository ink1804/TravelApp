package com.ink1804.core.analytics

import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val debugAnalyticsModule = module {
    single(named("debug")) { DebugAnalyticsDelegateImpl() } bind AnalyticsDelegate::class
}
