package com.ink1804.feature.profile

import org.koin.dsl.module

val profileModule = module {
    factory<ProfileComponent.Factory> {
        ProfileComponentImpl.Factory()
    }
}
