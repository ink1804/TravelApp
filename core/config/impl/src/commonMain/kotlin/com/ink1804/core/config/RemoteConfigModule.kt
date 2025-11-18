package com.ink1804.core.config

import org.koin.dsl.module

val remoteConfigModule = module {
    single<RemoteConfigRepository> { RemoteConfigRepositoryImpl() }
    single<FeatureToggleRepository> { FeatureToggleRepositoryImpl() }
}