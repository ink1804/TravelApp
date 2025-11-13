package com.ink1804.core.config

import com.ink1804.config.api.FeatureKey
import com.ink1804.config.api.FeatureToggleRepository

class FeatureToggleRepositoryImpl: FeatureToggleRepository {
    override fun isEnabled(featureKey: FeatureKey): Boolean {
        //todo storage.get(featureKey).isEnabled
        return false
    }

    override fun setEnabled(featureKey: FeatureKey, enabled: Boolean) {
        //todo storage.set(featureKey)
    }

    override fun getAll(): Map<FeatureKey, Boolean> {
        //todo storage.getAll()
        return mapOf()
    }

}