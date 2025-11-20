package com.ink1804.core.storage.sqldelight

import kotlinx.coroutines.flow.Flow

interface LocalStore<K : Any, V : Any> {
    fun observe(key: K): Flow<V?>
    fun observeAll(): Flow<List<V>>
    suspend fun get(key: K): V?
    suspend fun getAll(): List<V>
    suspend fun upsert(value: V)
    suspend fun upsertAll(values: List<V>)
    suspend fun delete(key: K)
    suspend fun clear()
}
