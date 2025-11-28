package com.ink1804.infra.sqldelight

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SqlDelightKeyValueStore<K : Any, V : Any>(
    private val queries: SqlDelightKeyValueQueries<V>,
    private val dispatcher: CoroutineDispatcher,
    private val keySelector: (V) -> K,
) : LocalStore<K, V> {

    override fun observe(key: K): Flow<V?> =
        queries.selectByKey(key.toString())
            .asFlow()
            .mapToOneOrNull(dispatcher)

    override fun observeAll(): Flow<List<V>> =
        queries.selectAll()
            .asFlow()
            .mapToList(dispatcher)

    override suspend fun get(key: K): V? =
        withContext(dispatcher) {
            queries.selectByKey(key.toString()).executeAsOneOrNull()
        }

    override suspend fun getAll(): List<V> =
        withContext(dispatcher) {
            queries.selectAll().executeAsList()
        }

    override suspend fun upsert(value: V) {
        withContext(dispatcher) {
            queries.upsert(value)
        }
    }

    override suspend fun upsertAll(values: List<V>) {
        withContext(dispatcher) {
            values.forEach { queries.upsert(it) }
        }
    }

    override suspend fun delete(key: K) {
        withContext(dispatcher) {
            queries.deleteByKey(key.toString())
        }
    }

    override suspend fun clear() {
        withContext(dispatcher) {
            queries.clear()
        }
    }
}
