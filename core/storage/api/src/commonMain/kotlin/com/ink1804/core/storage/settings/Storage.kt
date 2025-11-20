package com.ink1804.core.storage.settings

import kotlinx.coroutines.flow.Flow

interface Storage<T> {
    suspend fun update(value: T)
    suspend fun getOrNull(): T?
    suspend fun clear()
    fun changes(): Flow<T?>
}
