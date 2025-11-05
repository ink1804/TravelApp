package com.ink1804.feature.root.impl

import com.arkivanov.decompose.ComponentContext
import com.ink1804.feature.root.api.RootComponent
import com.ink1804.feature.root.api.RootEntry
import com.ink1804.feature.root.api.RootScreenProvider
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val rootModule = module {
    factory<RootComponent> { (ctx: ComponentContext) ->
        RootComponentImpl(ctx)
    }

    factory<RootEntry> {
        RootEntry { ctx ->
            get<RootComponent> { parametersOf(ctx) }
        }
    }

    single<RootScreenProvider> { RootScreenProviderImpl() }
}
