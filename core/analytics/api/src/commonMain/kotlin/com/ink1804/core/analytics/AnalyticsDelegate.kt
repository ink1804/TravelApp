package com.ink1804.core.analytics

interface Analytics: AnalyticsDelegate

interface AnalyticsDelegate {
    fun setUserProperty(key: String, value: Any?)
    fun sendEvent(key: String, params: Map<String, Any?> = emptyMap())
}
