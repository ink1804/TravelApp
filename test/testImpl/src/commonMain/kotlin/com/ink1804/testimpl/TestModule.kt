package com.ink1804.testimpl

import com.ink1804.core.storage.settings.Storage
import com.ink1804.core.storage.settings.StorageFactory
import com.ink1804.core.storage.settings.create
import com.ink1804.testapi.TestApi
import com.ink1804.testapi.User
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val testModule = module {
    singleOf(::TestImpl) bind TestApi::class

    single<Storage<User>> {
        get<StorageFactory>().create<User>(
            "UserEntity"
        )
    }
}
