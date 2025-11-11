package com.ink1804.feature.home

import com.ink1804.feature.home.HomeComponentImpl.Factory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    factory<HomeAnalytics> { HomeAnalyticsImpl(get()) }

    factoryOf(::Factory) bind HomeComponent.Factory::class
}
