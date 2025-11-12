package com.ink1804.feature.home

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    factory<HomeAnalytics> { HomeAnalyticsImpl(get()) }

    factoryOf(HomeComponentImpl::Factory) bind HomeComponent.Factory::class
}
