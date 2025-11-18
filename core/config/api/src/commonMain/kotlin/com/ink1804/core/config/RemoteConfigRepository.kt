package com.ink1804.core.config

interface RemoteConfigRepository {

    fun getConfig(remoteConfigKey: RemoteConfigKey)

    fun getAll(): Map<RemoteConfig, Boolean>

}
