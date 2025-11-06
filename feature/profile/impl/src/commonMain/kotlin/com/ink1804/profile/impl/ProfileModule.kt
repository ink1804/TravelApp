package com.ink1804.profile.impl

import com.ink1804.profile.api.ProfileComponent
import org.koin.dsl.module

val profileModule = module {
    factory<ProfileComponent.Factory> {
        ProfileComponentImpl.Factory()
    }
}
