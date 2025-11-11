package com.ink1804.core.di

import com.ink1804.core.analytics.compositeAnalyticsModule
import com.ink1804.core.app.appModule
import com.ink1804.feature.discovery.discoveryModule
import com.ink1804.feature.home.homeModule
import com.ink1804.feature.profile.profileModule
import com.ink1804.feature.root.rootModule
import com.ink1804.testimpl.testModule
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        appModule,
        compositeAnalyticsModule,
        rootModule,
        homeModule,
        discoveryModule,
        profileModule,
        testModule
    )
}