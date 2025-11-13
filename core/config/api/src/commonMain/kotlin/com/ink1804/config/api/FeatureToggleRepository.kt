package com.ink1804.config.api

interface FeatureToggleRepository {
    fun isEnabled(featureKey: FeatureKey): Boolean

    fun setEnabled(featureKey: FeatureKey, enabled: Boolean)

    fun getAll(): Map<FeatureKey, Boolean>

}
