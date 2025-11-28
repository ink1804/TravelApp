package com.ink1804.testimpl

import com.ink1804.core.storage.settings.Storage
import com.ink1804.data.auth.AuthRepository
import com.ink1804.data.config.FeatureToggleRepository
import com.ink1804.data.user.UserRepository
import com.ink1804.testapi.TestApi
import com.ink1804.testapi.User

class TestImpl(
    private val userStore: Storage<User>,
    private val featureToggleRepository: FeatureToggleRepository,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) : TestApi {

    override fun getString(): String {
        return platform()
    }

    override suspend fun userTest() {
        userRepository.getUserById("525c46bc-2039-454d-b1da-d9a76415dcba")
        authRepository.isAuthorized().collect {
            if (it) {
                userRepository.getCurrentUser()
            }
        }
    }

    override suspend fun put() {
        userStore.update(User("2", "John"))
//        localStore.upsert(UserEntity("1", "John"))
    }

    override suspend fun get(): User? {
        return userStore.getOrNull() //?: localStore.get("1")?.let { User(it.id, it.name) }
    }

    override suspend fun getAllKeys(): Map<String, Boolean> {
        return featureToggleRepository.getAll().associate { it.first to it.second }
    }
}
