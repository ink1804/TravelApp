package com.ink1804.core.config

import com.ink1804.config.api.FeatureToggleRepository
import com.ink1804.config.api.RemoteConfigRepository
import org.koin.dsl.module

val remoteConfigModule = module {
    single<RemoteConfigRepository> { RemoteConfigRepositoryImpl() }
    single<FeatureToggleRepository> { FeatureToggleRepositoryImpl() }
}