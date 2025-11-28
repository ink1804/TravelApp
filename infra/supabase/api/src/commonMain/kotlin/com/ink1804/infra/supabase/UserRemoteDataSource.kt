package com.ink1804.infra.supabase

import kotlinx.serialization.Serializable

@Serializable
data class SupabaseUser(
    val id: String,
    val name: String,
)

interface UserRemoteDataSource {
    suspend fun getOrCreateCurrentUser(): SupabaseUser
}