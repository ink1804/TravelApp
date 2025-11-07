package com.ink1804.core.logger

actual object Log {
    actual fun d(tag: String, message: String) { println("$tag:\uD83D\uDFE2: $message") } // "🟢"
    actual fun w(tag: String, message: String) { println("$tag:\uD83D\uDFE1: $message") } // "🟡"
    actual fun e(tag: String, message: String) { println("$tag:\uD83D\uDD34: $message") } // "🔴"
    actual fun i(tag: String, message: String) { println("$tag:\uD83D\uDD35: $message") } // "🔵"
}
