package com.ink1804.core.resources

interface ResourceProvider {
    fun getString(resId: String): String
}
