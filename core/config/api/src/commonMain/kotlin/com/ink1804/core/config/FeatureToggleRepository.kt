package com.ink1804.core.config

interface FeatureToggleRepository {
    fun isEnabled(featureKey: FeatureKey): Boolean

    fun setEnabled(featureKey: FeatureKey, enabled: Boolean)

    fun getAll(): Map<FeatureKey, Boolean>

}
