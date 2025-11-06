package com.ink1804.discovery.impl

import com.ink1804.discovery.api.DiscoveryComponent
import org.koin.dsl.module

val discoveryModule = module {
    factory<DiscoveryComponent.Factory> {
        DiscoveryComponentImpl.Factory()
    }
}
