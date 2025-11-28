package com.ink1804.infra.sqldelight

import app.cash.sqldelight.Query

interface SqlDelightKeyValueQueries<E : Any> {
    fun selectByKey(key: String): Query<E>
    fun selectAll(): Query<E>
    fun upsert(entity: E)
    fun deleteByKey(key: String)
    fun clear()
}
