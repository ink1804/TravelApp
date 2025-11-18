package com.ink1804.testimpl

import com.ink1804.testapi.TestApi
import org.koin.dsl.module

val testModule = module {
    single<TestApi> { TestImpl(get()) }
}
