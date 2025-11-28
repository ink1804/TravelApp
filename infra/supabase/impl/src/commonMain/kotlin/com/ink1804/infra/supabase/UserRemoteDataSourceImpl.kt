package com.ink1804.infra.supabase

import com.ink1804.core.logger.Log
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder

internal class UserRemoteDataSourceImpl(
    private val auth: Auth,
    private val users: PostgrestQueryBuilder,
) : UserRemoteDataSource {

    override suspend fun getOrCreateCurrentUser(): SupabaseUser {
        val authUser = auth.currentUserOrNull() ?: error("User is not authorized")

        val exitingUser = users
            .select(columns = Columns.list("id")) {
                filter { eq("id", authUser.id) }
            }
            .decodeList<SupabaseUser>()
            .firstOrNull()

        if (exitingUser != null) return exitingUser

        val newUser = SupabaseUser(
            id = authUser.id,
            name = authUser.email?.substringBefore('@') ?: "Unknown",
        )

        users.insert(newUser)

        return newUser
    }

    override suspend fun getUserById(id: String): SupabaseUser? {
        auth.currentUserOrNull() ?: error("User is not authorized")

        val exitingUser = users
            .select(columns = Columns.ALL) {
                filter { eq("id", id) }
            }
            .decodeList<SupabaseUser>()
            .firstOrNull()

        return exitingUser
    }
}
