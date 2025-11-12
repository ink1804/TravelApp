package com.ink1804.core.platform

import kotlinx.coroutines.flow.Flow

expect class ShakeDetector {
    val shakes: Flow<Unit>
    fun start()
    fun stop()
}
