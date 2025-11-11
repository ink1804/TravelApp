package com.ink1804.core.analytics

import com.ink1804.core.logger.Log

class DebugAnalyticsDelegateImpl: AnalyticsDelegate {
    override fun setUserProperty(key: String, value: Any?) {
        Log.d("DebugAnalytics", "UserProperty set [key: $key, value: $value]")
    }

    override fun sendEvent(key: String, params: Map<String, Any?>) {
        Log.d("DebugAnalytics", "Event sent [key: $key, params: $params]")
    }
}
