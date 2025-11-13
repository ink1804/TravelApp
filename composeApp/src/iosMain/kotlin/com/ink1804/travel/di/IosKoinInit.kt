package com.ink1804.travel.di

import com.ink1804.core.di.initKoin

fun doInitKoin() {
    initKoin(modules = getAppModules())
}
