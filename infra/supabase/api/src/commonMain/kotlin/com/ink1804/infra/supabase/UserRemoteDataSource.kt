package com.ink1804.infra.supabase

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupabaseUser(
    @SerialName("id")
    val id: String,
    @SerialName("display_name")
    val name: String,
)

interface UserRemoteDataSource {
    suspend fun getOrCreateCurrentUser(): SupabaseUser

    suspend fun getUserById(id: String): SupabaseUser?
}
