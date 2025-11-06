package com.ink1804.discovery.impl

import com.arkivanov.decompose.ComponentContext
import com.ink1804.discovery.api.DiscoveryComponent
import com.ink1804.discovery.api.DiscoveryEntry
import com.ink1804.discovery.api.DiscoveryScreenProvider
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val discoveryModule = module {
    factory<DiscoveryComponent> { (ctx: ComponentContext) ->
        DiscoveryComponentImpl(ctx)
    }

    factory<DiscoveryEntry> {
        DiscoveryEntry { ctx ->
            get<DiscoveryComponent> { parametersOf(ctx) }
        }
    }

    single<DiscoveryScreenProvider> { DiscoveryScreenProviderImpl() }
}
