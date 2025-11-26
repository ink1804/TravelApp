package com.ink1804.core.resources

import android.content.Context
import org.jetbrains.compose.resources.StringResource

class ResourceProviderImpl(
    private val context: Context,
): ResourceProvider  {
    override fun getString(resId: String): String {
        return "stub"
    }
}
