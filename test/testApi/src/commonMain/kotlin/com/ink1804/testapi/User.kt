package com.ink1804.testapi

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
)
