package com.ink1804.config.api

interface RemoteConfigRepository {

    fun getConfig(remoteConfigKey: RemoteConfigKey)

    fun getAll(): Map<RemoteConfig, Boolean>

}
