package com.ink1804.feature.discovery

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val discoveryModule = module {
    factoryOf(DiscoveryComponentImpl::Factory) bind DiscoveryComponent.Factory::class
}
