package com.ink1804.feature.root

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val rootModule = module {
    factoryOf(RootComponentImpl::Factory) bind RootComponent.Factory::class
}
