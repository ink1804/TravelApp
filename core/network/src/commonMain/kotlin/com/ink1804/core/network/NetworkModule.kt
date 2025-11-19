package com.ink1804.core.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

expect fun createHttpClient(block: HttpClientConfig<*>.() -> Unit = {}): HttpClient

val networkModule = module {
    single<HttpClient> {
        createHttpClient {
            installJsonConfig()
            installLogging()
            installDefaultRequest()
        }
    }
}

internal fun HttpClientConfig<*>.installJsonConfig() {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = true
            }
        )
    }
}

internal fun HttpClientConfig<*>.installLogging() {
    //todo BuildKonfig.DEBUG
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}

internal fun HttpClientConfig<*>.installDefaultRequest() {
    install(DefaultRequest) {
        header("Content-Type", "application/json")
    }
}
