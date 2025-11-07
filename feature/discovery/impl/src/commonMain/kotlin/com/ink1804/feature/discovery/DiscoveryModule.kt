package com.ink1804.feature.discovery

import org.koin.dsl.module

val discoveryModule = module {
    factory<DiscoveryComponent.Factory> {
        DiscoveryComponentImpl.Factory()
    }
}
