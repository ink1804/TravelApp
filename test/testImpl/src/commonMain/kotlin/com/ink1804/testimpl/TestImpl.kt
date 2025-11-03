package com.ink1804.testimpl

import com.ink1804.testapi.TestApi

class TestImpl: TestApi {
    override fun getString(): String {
        return platform()
    }
}