package com.ink1804.core.storage

interface DbTransactionRunner {
    suspend fun <T> run(block: suspend () -> T): T
}
