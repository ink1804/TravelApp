package com.ink1804.travel.di

import com.ink1804.core.analytics.compositeAnalyticsModule
import com.ink1804.core.app.appModule
import com.ink1804.core.config.remoteConfigModule
import com.ink1804.core.database.databaseModule
import com.ink1804.core.firebase.firebaseModule
import com.ink1804.core.network.networkModule
import com.ink1804.core.platform.platformModule
import com.ink1804.core.resources.resourcesModule
import com.ink1804.core.settings.settingsModule
import com.ink1804.core.storage.storageModule
import com.ink1804.feature.debug.debugModule
import com.ink1804.feature.discovery.discoveryModule
import com.ink1804.feature.home.homeModule
import com.ink1804.feature.onboarding.impl.onboardingModule
import com.ink1804.feature.profile.profileModule
import com.ink1804.feature.root.rootModule
import com.ink1804.testimpl.testModule
import org.koin.core.module.Module

fun getAppModules(): List<Module> = buildList {
    // Core modules
    add(appModule)
    add(resourcesModule)
    add(firebaseModule)
    add(settingsModule)
    add(networkModule)
    add(platformModule)
    add(databaseModule)
    add(compositeAnalyticsModule)
    add(remoteConfigModule)
    add(storageModule)

    // Feature modules
    add(testModule)
    add(rootModule)
    add(debugModule)
    add(onboardingModule)
    add(homeModule)
    add(discoveryModule)
    add(profileModule)
}
