package com.ink1804.profile.impl

import com.arkivanov.decompose.ComponentContext
import com.ink1804.profile.api.ProfileComponent
import com.ink1804.profile.api.ProfileEntry
import com.ink1804.profile.api.ProfileScreenProvider
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val profileModule = module {
    factory<ProfileComponent> { (ctx: ComponentContext) ->
        ProfileComponentImpl(ctx)
    }

    factory<ProfileEntry> {
        ProfileEntry { ctx ->
            get<ProfileComponent> { parametersOf(ctx) }
        }
    }

    single<ProfileScreenProvider> { ProfileScreenProviderImpl() }
}
