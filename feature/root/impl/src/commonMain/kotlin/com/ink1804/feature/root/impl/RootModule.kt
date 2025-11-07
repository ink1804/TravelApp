package com.ink1804.feature.root.impl

import com.ink1804.feature.root.api.RootComponent
import org.koin.dsl.module

val rootModule = module {
    factory<RootComponent.Factory> {
        RootComponentImpl.Factory(
            homeComponentFactory = get(),
        )
    }
}
