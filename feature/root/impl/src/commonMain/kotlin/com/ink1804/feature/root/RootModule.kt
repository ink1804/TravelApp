package com.ink1804.feature.root

import org.koin.dsl.module

val rootModule = module {
    factory<RootComponent.Factory> {
        RootComponentImpl.Factory(
            homeComponentFactory = get(),
        )
    }
}
