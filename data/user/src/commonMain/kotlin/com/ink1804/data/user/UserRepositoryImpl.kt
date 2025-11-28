package com.ink1804.data.user

import com.ink1804.infra.database.UserEntity
import com.ink1804.infra.sqldelight.LocalStore
import com.ink1804.infra.supabase.SupabaseUser
import com.ink1804.infra.supabase.UserRemoteDataSource

interface UserRepository {
    suspend fun getCurrentUser(): SupabaseUser
    suspend fun getUserById(id: String): SupabaseUser?
}

internal class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: LocalStore<String, UserEntity>,
) : UserRepository {

    private var currentUser: SupabaseUser? = null

    override suspend fun getCurrentUser(): SupabaseUser {
        currentUser?.let { return it }

        return remoteDataSource.getOrCreateCurrentUser().also { currentUser = it }
    }

    override suspend fun getUserById(id: String): SupabaseUser? {
        return localDataSource.get(id)
            ?.let { SupabaseUser(it.id, it.name) }
            ?: remoteDataSource.getUserById(id)
                ?.also { localDataSource.upsert(UserEntity(it.id, it.name)) }
    }
}
