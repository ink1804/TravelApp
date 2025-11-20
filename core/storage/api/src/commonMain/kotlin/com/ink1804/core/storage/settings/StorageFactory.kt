package com.ink1804.core.storage.settings

import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

interface StorageFactory {
    fun <T> create(
        key: String,
        serializer: KSerializer<T>
    ): Storage<T>
}

inline fun <reified T> StorageFactory.create(key: String): Storage<T> {
    return create(key, serializer())
}
