package com.ink1804.infra.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.FirebaseRemoteConfig
import dev.gitlive.firebase.remoteconfig.remoteConfig
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds

internal class RemoteConfigDataSourceImpl(
    private val remoteConfig: FirebaseRemoteConfig
) : RemoteConfigDataSource {

    private var isActivated = false

    override suspend fun activate(): Boolean {
        if (isActivated) return true

        remoteConfig.settings {
            minimumFetchInterval = 12.hours
            fetchTimeout = 60.seconds
        }

        isActivated = remoteConfig.fetchAndActivate()
        return isActivated
    }

    override suspend fun reset() {
        isActivated = false
        remoteConfig.reset()
    }

    override suspend fun getAll(prefix: String?): List<Pair<String, String>> {
        activate()
        return remoteConfig
            .all
            .let { allConfigs ->
                prefix?.let { prefix ->
                    allConfigs.filter { it.key.startsWith(prefix) }
                } ?: allConfigs
            }
            .map { it.key to it.value.asString() }.toList()
    }

    override suspend fun getConfig(key: String): String {
        activate()
        return Firebase.remoteConfig.getValue(key).asString()
    }

    override suspend fun getKeysByPrefix(prefix: String): List<String> {
        activate()
        return Firebase.remoteConfig.getKeysByPrefix(prefix).toList()
    }
}
