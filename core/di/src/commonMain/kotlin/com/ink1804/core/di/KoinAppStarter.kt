package com.ink1804.core.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * Initialize Koin with the provided modules.
 * This function should be called from the app layer with all required modules.
 *
 * @param modules List of Koin modules to register
 * @param config Optional configuration block for platform-specific setup
 */
fun initKoin(
    modules: List<Module>,
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(modules)
    }
}
