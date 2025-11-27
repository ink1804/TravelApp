package com.ink1804.core.firebase

import android.content.Context
import com.google.firebase.FirebaseApp

actual object FirebaseInitializer {
    actual fun initialize(context: Any?) {
        val ctx = context as? Context ?: error("FirebaseInitializer.initialize: Context required on Android")
        FirebaseApp.initializeApp(ctx)
    }
}
