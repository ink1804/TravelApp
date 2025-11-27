package com.ink1804.core.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.dsl.module

val firebaseModule = module {
    single<AuthRepository> { AuthRepositoryImpl(Firebase.auth) }
}

expect object FirebaseInitializer {
    fun initialize(context: Any? = null)
}
