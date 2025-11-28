package com.ink1804.infra.supabase

import com.ink1804.core.logger.Log
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

internal class SuperbaseAuthRepositoryImpl(
    private val auth: Auth,
) : AuthDataSource {

    override val isAuthorized: Flow<Boolean> = auth
        .sessionStatus.map { it is SessionStatus.Authenticated }
        .onEach { Log.d("Auth", "isAuthorized: ${auth.currentUserOrNull()}") }
}
