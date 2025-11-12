package com.ink1804.core.platform

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

actual class ShakeDetector {
    actual fun start() = Unit
    actual fun stop() = Unit
    actual val shakes: Flow<Unit> = MutableSharedFlow<Unit>()
}
