package com.ink1804.core.config

interface FeatureToggleRepository {
    suspend fun isEnabled(featureKey: FeatureKey): Boolean
    fun setEnabled(featureKey: FeatureKey, enabled: Boolean)
    suspend fun getAll(): List<Pair<String, Boolean>>
}
