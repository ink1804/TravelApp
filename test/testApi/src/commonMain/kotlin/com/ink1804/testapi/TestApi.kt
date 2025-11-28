package com.ink1804.testapi

interface TestApi {
    fun getString(): String
    suspend fun put()
    suspend fun get(): User?
    suspend fun getAllKeys(): Map<String, Boolean>
    suspend fun userTest()
}
