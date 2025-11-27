package com.ink1804.core.config

import com.ink1804.core.firebase.FirebaseRemoteConfigRepository

class FeatureToggleRepositoryImpl(
    private val firebaseRemoteConfigRepository: FirebaseRemoteConfigRepository,
): FeatureToggleRepository {
    override suspend fun isEnabled(featureKey: FeatureKey): Boolean {
        //todo add local storage
        return firebaseRemoteConfigRepository.getConfig(featureKey.key).toBoolean()
    }

    override fun setEnabled(featureKey: FeatureKey, enabled: Boolean) {
        //todo add local storage
    }

    override suspend fun getAll(): List<Pair<String, Boolean>> {
        //todo add local storage
        return firebaseRemoteConfigRepository.getAll("exp_").map { (key, value) ->
            key to value.toBoolean()
        }
    }
}
