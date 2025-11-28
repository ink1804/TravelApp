package com.ink1804.data.auth

import com.ink1804.infra.supabase.AuthDataSource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isAuthorized(): Flow<Boolean>
}

internal class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
) : AuthRepository {

    override fun isAuthorized(): Flow<Boolean> {
        return authDataSource.isAuthorized
    }
}
