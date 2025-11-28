package com.ink1804.data.config

import com.ink1804.infra.firebase.RemoteConfigDataSource

class RemoteConfigRepositoryImpl(
    private val remoteConfigDataSource: RemoteConfigDataSource,
): RemoteConfigRepository {
    override suspend fun getConfig(remoteConfigKey: RemoteConfigKey): String {
        //todo add storage and serialization
        return remoteConfigDataSource.getConfig(remoteConfigKey.key)
    }

    override suspend fun getAll(): List<Pair<String, String>> {
        //todo add storage
        return remoteConfigDataSource.getAll("config_")
    }
}
