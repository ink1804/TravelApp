package com.ink1804.core.di

import com.ink1804.core.analytics.compositeAnalyticsModule
import com.ink1804.core.app.appModule
import com.ink1804.core.platform.platformModule
import com.ink1804.feature.debug.debugModule
import com.ink1804.feature.discovery.discoveryModule
import com.ink1804.feature.home.homeModule
import com.ink1804.feature.profile.profileModule
import com.ink1804.feature.root.rootModule
import com.ink1804.testimpl.testModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            platformModule,
            compositeAnalyticsModule,
            rootModule,
            debugModule,
            homeModule,
            discoveryModule,
            profileModule,
            testModule,
        )
    }
}
