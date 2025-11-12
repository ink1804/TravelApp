package com.ink1804.core.platform

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.core.content.getSystemService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.math.sqrt

actual class ShakeDetector(
    private val context: Context,
) {
    private val sensorManager = context.getSystemService<SensorManager>()
    private val accel = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val _shakes = MutableSharedFlow<Unit>(replay = 0, extraBufferCapacity = 1)
    actual val shakes: Flow<Unit> = _shakes.asSharedFlow()

    private var lastShakeTs = 0L
    private val listener = object : SensorEventListener {
        override fun onSensorChanged(e: SensorEvent) {
            val ax = e.values[0] / SensorManager.GRAVITY_EARTH
            val ay = e.values[1] / SensorManager.GRAVITY_EARTH
            val az = e.values[2] / SensorManager.GRAVITY_EARTH
            val g = sqrt(ax * ax + ay * ay + az * az)
            val now = System.currentTimeMillis()
            if (g > 2.7 && now - lastShakeTs > 600) { // пороги: ~2.7g и антидребезг
                lastShakeTs = now
                _shakes.tryEmit(Unit)
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
    }

    actual fun start() {
        sensorManager?.registerListener(listener, accel, SensorManager.SENSOR_DELAY_GAME)
    }

    actual fun stop() {
        sensorManager?.unregisterListener(listener)
    }
}
