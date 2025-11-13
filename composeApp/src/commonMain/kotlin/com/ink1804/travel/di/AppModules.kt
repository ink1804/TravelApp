package com.ink1804.travel.di

import com.ink1804.core.analytics.compositeAnalyticsModule
import com.ink1804.core.app.appModule
import com.ink1804.core.config.remoteConfigModule
import com.ink1804.core.platform.platformModule
import com.ink1804.feature.debug.debugModule
import com.ink1804.feature.discovery.discoveryModule
import com.ink1804.feature.home.homeModule
import com.ink1804.feature.profile.profileModule
import com.ink1804.feature.root.rootModule
import org.koin.core.module.Module

fun getAppModules(): List<Module> = buildList {
    // Core modules
    add(appModule)
    add(platformModule)
    add(compositeAnalyticsModule)
    add(remoteConfigModule)

    // Feature modules
    add(rootModule)
    add(debugModule)
    add(homeModule)
    add(discoveryModule)
    add(profileModule)
}
