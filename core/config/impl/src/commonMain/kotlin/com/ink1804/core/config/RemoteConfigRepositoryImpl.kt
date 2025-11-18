package com.ink1804.core.config

class RemoteConfigRepositoryImpl: RemoteConfigRepository {
    override fun getConfig(remoteConfigKey: RemoteConfigKey) {
        //todo storage.get(remoteConfigKey).value
    }

    override fun getAll(): Map<RemoteConfig, Boolean> {
        //todo storage.getAll(remoteConfigKey)
        return mapOf()
    }
}
