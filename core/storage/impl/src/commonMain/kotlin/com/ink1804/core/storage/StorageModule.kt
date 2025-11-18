package com.ink1804.core.storage

import com.ink1804.core.database.AppDatabase
import com.ink1804.core.storage.user.UserSqlDelightQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.bind
import org.koin.dsl.module

val storageModule = module {
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
