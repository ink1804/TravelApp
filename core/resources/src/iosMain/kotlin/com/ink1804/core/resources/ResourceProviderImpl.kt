package com.ink1804.core.resources


class ResourceProviderImpl(
): ResourceProvider  {
    override fun getString(resId: String): String {
//        NSBundle.mainBundle.localizedStringForKey(resId, resId, null)
        return "stub"
    }
}
