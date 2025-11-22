package com.ink1804.feature.profile

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val profileModule = module {
    factoryOf(ProfileComponentImpl::Factory) bind ProfileComponent.Factory::class
}
