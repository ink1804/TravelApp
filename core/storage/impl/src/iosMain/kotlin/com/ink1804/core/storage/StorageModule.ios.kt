package com.ink1804.core.storage

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class)
internal actual val settingsModule: Module = module {
    single<NSUserDefaults> { NSUserDefaults.standardUserDefaults }
    single<FlowSettings> { NSUserDefaultsSettings(get()).toFlowSettings(Dispatchers.IO) }
}
