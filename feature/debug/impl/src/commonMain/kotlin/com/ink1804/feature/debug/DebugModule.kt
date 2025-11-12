package com.ink1804.feature.debug

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val debugModule = module {
    factoryOf(DebugMenuComponentImpl::Factory) bind DebugMenuComponent.Factory::class
}