package com.ink1804.home.impl

import com.ink1804.home.api.HomeComponent
import org.koin.dsl.module

val homeModule = module {
    factory<HomeComponent.Factory> {
        HomeComponentImpl.Factory(
            profileComponentFactory = get(),
            discoveryComponentFactory = get(),
        )
    }
}
