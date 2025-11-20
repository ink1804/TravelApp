package com.ink1804.core.storage.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSettingsApi::class)
class SettingsStorageFactory(
    private val settings: FlowSettings,
    private val json: Json,
) : StorageFactory {

    override fun <T> create(
        key: String,
        serializer: KSerializer<T>
    ): Storage<T> {
        return SettingsStorage(
            key = key,
            serializer = serializer,
            settings = settings,
            json = json,
        )
    }
}
