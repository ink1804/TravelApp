package com.ink1804.core.platform

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import platform.CoreMotion.CMMotionManager
import platform.Foundation.NSDate
import platform.Foundation.NSOperationQueue
import platform.Foundation.timeIntervalSince1970
import kotlin.math.sqrt

actual class ShakeDetector {
    private val _shakes = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    actual val shakes: Flow<Unit> = _shakes.asSharedFlow()

    private val motion = CMMotionManager().apply { accelerometerUpdateInterval = 1.0 / 60.0 }
    private var lastTs = 0.0

    @OptIn(ExperimentalForeignApi::class)
    actual fun start() {
        if (motion.isAccelerometerAvailable()) {
            motion.accelerometerUpdateInterval = 1.0 / 60.0
            motion.startAccelerometerUpdatesToQueue(NSOperationQueue.mainQueue()) { data, _ ->
                data?.acceleration?.useContents {
                    val g = sqrt(x * x + y * y + z * z)
                    val now = NSDate().timeIntervalSince1970
                    if (g > 2.2 && now - lastTs > 0.6) {
                        lastTs = now
                        _shakes.tryEmit(Unit)
                    }
                }
            }
        }
    }

    actual fun stop() {
        if (motion.isAccelerometerActive()) motion.stopAccelerometerUpdates()
    }
}
