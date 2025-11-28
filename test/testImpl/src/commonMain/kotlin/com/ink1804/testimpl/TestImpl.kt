package com.ink1804.testimpl

import com.ink1804.core.database.UserEntity
import com.ink1804.core.storage.settings.Storage
import com.ink1804.core.storage.sqldelight.LocalStore
import com.ink1804.data.config.FeatureToggleRepository
import com.ink1804.testapi.TestApi
import com.ink1804.testapi.User

class TestImpl(
    private val localStore: LocalStore<String, UserEntity>,
    private val userStore: Storage<User>,
    private val featureToggleRepository: FeatureToggleRepository,
) : TestApi {
    override fun getString(): String {
        return platform()
    }

    override suspend fun put() {
        userStore.update(User("2", "John"))
        localStore.upsert(UserEntity("1", "John"))
    }

    override suspend fun get(): User? {
        return userStore.getOrNull() ?: localStore.get("1")?.let { User(it.id, it.name) }
    }

    override suspend fun getAllKeys(): Map<String, Boolean> {
        return featureToggleRepository.getAll().associate { it.first to it.second }
    }
}
