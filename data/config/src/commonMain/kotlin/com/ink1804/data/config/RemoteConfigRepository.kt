package com.ink1804.data.config

interface RemoteConfigRepository {
    suspend fun getConfig(remoteConfigKey: RemoteConfigKey): String
    suspend fun getAll(): List<Pair<String, String>>
}
