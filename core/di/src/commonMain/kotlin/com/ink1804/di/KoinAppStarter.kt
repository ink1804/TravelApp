package com.ink1804.di

import com.ink1804.app.appModule
import com.ink1804.feature.root.impl.rootModule
import com.ink1804.testimpl.testModule
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        appModule,
        rootModule,
        testModule
    )
}