package com.ink1804.core.storage.sqldelight

interface DbTransactionRunner {
    suspend fun <T> run(block: suspend () -> T): T
}
