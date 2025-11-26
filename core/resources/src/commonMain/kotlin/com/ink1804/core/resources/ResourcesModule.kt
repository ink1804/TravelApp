package com.ink1804.core.resources

import org.koin.core.module.Module
import org.koin.dsl.module
import travelapp.core.resources.generated.resources.Res

val resourcesModule: Module = module {
    //todo maybe resource provider should be platform specific
//    single<ResourceProvider> { ResourceProviderImpl() }
}

typealias AppStrings = Res.string
typealias AppDrawable = Res.drawable
