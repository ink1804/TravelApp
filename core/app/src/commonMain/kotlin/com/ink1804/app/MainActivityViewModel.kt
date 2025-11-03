package com.ink1804.app

import androidx.lifecycle.ViewModel
import com.ink1804.testapi.TestApi

class MainActivityViewModel(
    private val testApi: TestApi
) : ViewModel() {

    fun getString(): String {
        return testApi.getString()
    }
}