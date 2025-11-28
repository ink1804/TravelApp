package com.ink1804.infra.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.remoteConfig
import org.koin.dsl.module

val firebaseModule = module {
    single<RemoteConfigDataSource> { RemoteConfigDataSourceImpl(Firebase.remoteConfig) }
}
