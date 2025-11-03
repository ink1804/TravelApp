package org.ink1804.travel

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform