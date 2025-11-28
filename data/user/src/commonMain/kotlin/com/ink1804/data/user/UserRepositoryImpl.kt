package com.ink1804.data.user

import com.ink1804.infra.supabase.SupabaseUser
import com.ink1804.infra.supabase.UserRemoteDataSource

interface UserRepository {
    suspend fun getCurrentUser(): SupabaseUser
}

internal class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
): UserRepository {

    private var currentUser: SupabaseUser? = null

    override suspend fun getCurrentUser(): SupabaseUser {
        currentUser?.let { return it }

        return remoteDataSource.getOrCreateCurrentUser().also { currentUser = it }
    }
}
