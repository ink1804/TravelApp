package com.ink1804.core.storage

import com.ink1804.core.storage.settings.SettingsStorageFactory
import com.ink1804.core.storage.settings.StorageFactory
import com.russhwolf.settings.ExperimentalSettingsApi
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val settingsModule: Module

@OptIn(ExperimentalSettingsApi::class)
val storageModule = module {
    includes(settingsModule)

    single<StorageFactory> {
        SettingsStorageFactory(
            settings = get()    ,
            json = Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
            }
        )
    }
}
