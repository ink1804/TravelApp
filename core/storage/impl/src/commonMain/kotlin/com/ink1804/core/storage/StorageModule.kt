package com.ink1804.core.storage

import com.ink1804.core.database.AppDatabase
import com.ink1804.core.storage.settings.SettingsStorageFactory
import com.ink1804.core.storage.settings.StorageFactory
import com.ink1804.core.storage.sqldelight.LocalStore
import com.ink1804.core.storage.sqldelight.SqlDelightKeyValueStore
import com.ink1804.core.storage.user.UserSqlDelightQueries
import com.russhwolf.settings.ExperimentalSettingsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

internal expect val settingsModule: Module

@OptIn(ExperimentalSettingsApi::class)
val storageModule = module {
    includes(settingsModule)

    single<StorageFactory> {
        SettingsStorageFactory(
            settings = get()    ,
            json = Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
            }
        )
    }

    single {
        val db: AppDatabase = get()
        val dispatcher = Dispatchers.IO

        val queries = UserSqlDelightQueries(db.userQueries)

        SqlDelightKeyValueStore(
            queries = queries,
            dispatcher = dispatcher,
            keySelector = { it.id } // UserEntity.id : String
        )
    } bind LocalStore::class // LocalStore<String, UserEntity>
}
