package com.ink1804.core.config

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteConfigModule = module {
    singleOf(::RemoteConfigRepositoryImpl) bind RemoteConfigRepository::class
    singleOf(::FeatureToggleRepositoryImpl) bind FeatureToggleRepository::class
}
