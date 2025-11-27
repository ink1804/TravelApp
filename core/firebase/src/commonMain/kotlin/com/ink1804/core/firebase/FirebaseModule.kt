package com.ink1804.core.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.remoteconfig.remoteConfig
import org.koin.dsl.module

val firebaseModule = module {
    single<AuthRepository> { AuthRepositoryImpl(Firebase.auth) }
    single<FirebaseRemoteConfigRepository> { FirebaseRemoteConfigRepositoryImpl(Firebase.remoteConfig) }
}

expect object FirebaseInitializer {
    fun initialize(context: Any? = null)
}
