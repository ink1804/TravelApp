@file:OptIn(ExperimentalSettingsApi::class)

package com.ink1804.core.storage.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class SettingsStorage<T>(
    private val key: String,
    private val serializer: KSerializer<T>,
    private val settings: FlowSettings,
    private val json: Json,
) : Storage<T> {

    override suspend fun update(value: T) {
        val encoded = json.encodeToString(serializer, value)
        settings.putString(key, encoded)
    }

    override suspend fun getOrNull(): T? {
        val encoded = settings.getStringOrNull(key) ?: return null
        return runCatching { json.decodeFromString(serializer, encoded) }.getOrNull()
    }

    override suspend fun clear() {
        settings.remove(key)
    }

    override fun changes(): Flow<T?> =
        settings.getStringFlow(key, defaultValue = "")
            .map { str ->
                if (str.isEmpty()) null
                else runCatching { json.decodeFromString(serializer, str) }.getOrNull()
            }
}
