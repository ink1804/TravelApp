package com.ink1804.data.config

import com.ink1804.infra.firebase.RemoteConfigDataSource

class FeatureToggleRepositoryImpl(
    private val remoteConfigDataSource: RemoteConfigDataSource,
): FeatureToggleRepository {
    override suspend fun isEnabled(featureKey: FeatureKey): Boolean {
        //todo add local storage
        return remoteConfigDataSource.getConfig(featureKey.key).toBoolean()
    }

    override fun setEnabled(featureKey: FeatureKey, enabled: Boolean) {
        //todo add local storage
    }

    override suspend fun getAll(): List<Pair<String, Boolean>> {
        //todo add local storage
        return remoteConfigDataSource.getAll("exp_").map { (key, value) ->
            key to value.toBoolean()
        }
    }
}
