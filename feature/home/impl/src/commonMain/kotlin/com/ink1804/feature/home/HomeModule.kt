package com.ink1804.feature.home

import org.koin.dsl.module

val homeModule = module {
    factory<HomeComponent.Factory> {
        HomeComponentImpl.Factory(
            profileComponentFactory = get(),
            discoveryComponentFactory = get(),
        )
    }
}
