package com.ink1804.core.storage

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.SharedPreferencesSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
internal actual val settingsModule: Module = module {
    single<SharedPreferences> { androidContext().getSharedPreferences("app", Context.MODE_PRIVATE) }
    single<FlowSettings> { SharedPreferencesSettings(get()).toFlowSettings(Dispatchers.IO) }
}
