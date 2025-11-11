package com.ink1804.feature.home

import com.ink1804.core.analytics.Analytics

class HomeAnalyticsImpl(
    private val analytics: Analytics,
) : HomeAnalytics {
    override fun screenOpened(screenId: String) {
        analytics.sendEvent("screen_opened", mapOf("id" to screenId))
    }
}
