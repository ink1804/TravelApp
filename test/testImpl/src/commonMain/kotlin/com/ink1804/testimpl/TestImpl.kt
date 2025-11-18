package com.ink1804.testimpl

import com.ink1804.core.database.UserEntity
import com.ink1804.core.storage.LocalStore
import com.ink1804.testapi.TestApi
import com.ink1804.testapi.User

class TestImpl(
    private val localStore: LocalStore<String, UserEntity>,
) : TestApi {
    override fun getString(): String {
        return platform()
    }

    override suspend fun put() {
        localStore.upsert(UserEntity("1", "John"))
    }

    override suspend fun get(): User?{
        return localStore.get("1")?.let { User(it.id, it.name) }
    }
}
