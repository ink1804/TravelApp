package com.ink1804.core.config

import com.ink1804.core.firebase.FirebaseRemoteConfigRepository

class RemoteConfigRepositoryImpl(
    private val firebaseRemoteConfigRepository: FirebaseRemoteConfigRepository,
): RemoteConfigRepository {
    override suspend fun getConfig(remoteConfigKey: RemoteConfigKey): String {
        //todo add storage and serialization
        return firebaseRemoteConfigRepository.getConfig(remoteConfigKey.key)
    }

    override suspend fun getAll(): List<Pair<String, String>> {
        //todo add storage
        return firebaseRemoteConfigRepository.getAll("config_")
    }
}
