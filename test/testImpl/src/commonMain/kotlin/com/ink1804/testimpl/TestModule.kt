package com.ink1804.testimpl

import com.ink1804.core.storage.settings.Storage
import com.ink1804.core.storage.settings.StorageFactory
import com.ink1804.core.storage.settings.create
import com.ink1804.testapi.TestApi
import com.ink1804.testapi.User
import org.koin.dsl.module

val testModule = module {
    single<TestApi> { TestImpl(get(), get()) }

    single<Storage<User>> {
        get<StorageFactory>().create<User>(
            "UserEntity"
        )
    }
}
