package com.ink1804.core.firebase

import dev.gitlive.firebase.auth.FirebaseAuth
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
}
