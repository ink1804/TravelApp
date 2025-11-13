package com.ink1804.config.api

enum class FeatureKey(val key: String) {
    TestFeature("feature_test")
}

enum class RemoteConfigKey(val key: String) {
    TestConfig("config_test")
}

sealed class RemoteConfig(val key: String) {
    data class TestConfig(val value: String) : RemoteConfig(RemoteConfigKey.TestConfig.key)

}
