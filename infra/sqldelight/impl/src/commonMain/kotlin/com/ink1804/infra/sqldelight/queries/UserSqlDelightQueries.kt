package com.ink1804.infra.sqldelight.queries

import app.cash.sqldelight.Query
import com.ink1804.infra.database.UserEntity
import com.ink1804.infra.database.UserQueries
import com.ink1804.infra.sqldelight.SqlDelightKeyValueQueries

class UserSqlDelightQueries(
    private val q: UserQueries
) : SqlDelightKeyValueQueries<UserEntity> {

    override fun selectByKey(key: String): Query<UserEntity> =
        q.selectById(key)

    override fun selectAll(): Query<UserEntity> =
        q.selectAll()

    override fun upsert(entity: UserEntity) {
        q.upsert(
            id = entity.id,
            name = entity.name,
        )
    }

    override fun deleteByKey(key: String) {
        q.deleteById(key)
    }

    override fun clear() {
        q.clearAll()
    }
}
