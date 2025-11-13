package com.ink1804.core.config

import com.ink1804.config.api.RemoteConfig
import com.ink1804.config.api.RemoteConfigKey
import com.ink1804.config.api.RemoteConfigRepository

class RemoteConfigRepositoryImpl: RemoteConfigRepository {
    override fun getConfig(remoteConfigKey: RemoteConfigKey) {
        //todo storage.get(remoteConfigKey).value
    }

    override fun getAll(): Map<RemoteConfig, Boolean> {
        //todo storage.getAll(remoteConfigKey)
        return mapOf()
    }
}
