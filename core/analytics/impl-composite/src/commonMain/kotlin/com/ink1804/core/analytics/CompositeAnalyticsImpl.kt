package com.ink1804.core.analytics

class CompositeAnalyticsImpl(
    private val delegates: List<AnalyticsDelegate>
) : Analytics {

    override fun setUserProperty(key: String, value: Any?) {
        delegates.forEach { it.setUserProperty(key, value) }
    }

    override fun sendEvent(key: String, params: Map<String, Any?>) {
        delegates.forEach { it.sendEvent(key, params) }
    }

}
