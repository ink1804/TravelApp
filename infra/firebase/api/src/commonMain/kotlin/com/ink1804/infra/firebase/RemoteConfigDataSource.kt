package com.ink1804.infra.firebase

interface RemoteConfigDataSource {
    suspend fun activate(): Boolean
    suspend fun reset()
    suspend fun getConfig(key: String): String
    suspend fun getKeysByPrefix(prefix: String): List<String>
    suspend fun getAll(prefix: String? = null): List<Pair<String, String>>
}
