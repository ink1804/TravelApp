package com.ink1804.infra.sqldelight

import com.ink1804.infra.database.AppDatabase
import com.ink1804.infra.sqldelight.queries.UserSqlDelightQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

expect val databasePlatformModule: Module

val sqldelightModule = module {
    includes(databasePlatformModule)
    single { createDatabase(get()) }

    single {
        SqlDelightKeyValueStore(
            queries = UserSqlDelightQueries(get<AppDatabase>().userQueries),
            dispatcher = Dispatchers.IO,
            keySelector = { it.id } // UserEntity.id : String
        )
    } bind LocalStore::class // LocalStore<String, UserEntity>
}
