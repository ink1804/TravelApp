package com.ink1804.infra.sqldelight

import app.cash.sqldelight.db.SqlDriver
import com.ink1804.infra.database.AppDatabase

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DatabaseDriverFactory): AppDatabase {
    val driver = driverFactory.createDriver()
    return AppDatabase(driver)
}
