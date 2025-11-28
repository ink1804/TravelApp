package com.ink1804.infra.supabase

import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    val isAuthorized: Flow<Boolean>
}
