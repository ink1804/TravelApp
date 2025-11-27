package com.ink1804.core.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.remoteconfig.remoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class AppUser(
    val id: String,
    val email: String?,
)

interface AuthRepository {
    val currentUser: Flow<AppUser?>
    suspend fun singIn(email: String, password: String)
    suspend fun singOut()
    suspend fun featureKeys(): Map<String, Boolean>
}

internal class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
) : AuthRepository {
    override val currentUser: Flow<AppUser?> = auth.authStateChanged.map { user ->
        user?.let {
            AppUser(
                id = it.uid,
                email = it.email,
            )
        }
    }

    override suspend fun singIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun singOut() {
        auth.signOut()
    }

    override suspend fun featureKeys(): Map<String, Boolean> {
        Firebase.remoteConfig.fetchAndActivate()
        return Firebase.remoteConfig.all.map { it.key to it.value.asBoolean() }.toMap()
    }
}
