package com.ink1804.core.logger

import corelogger.BuildKonfig
import kotlin.experimental.ExperimentalNativeApi

actual object Log {

    /**Log with 🟢 color*/
    @OptIn(ExperimentalNativeApi::class)
    actual fun d(tag: String, message: String) {
        if (BuildKonfig.DEBUG) println("$tag:\uD83D\uDFE2: $message")
    }

    /**Log with 🟡 color*/
    actual fun w(tag: String, message: String) {
        println("$tag:\uD83D\uDFE1: $message")
    }

    /**Log with 🔴 color*/
    actual fun e(tag: String, message: String) {
        println("$tag:\uD83D\uDD34: $message")
    }

    /**Log with 🔵 color*/
    actual fun i(tag: String, message: String) {
        println("$tag:\uD83D\uDD35: $message")
    }
}
