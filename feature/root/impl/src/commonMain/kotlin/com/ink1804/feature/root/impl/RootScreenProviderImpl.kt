package com.ink1804.feature.root.impl

import androidx.compose.runtime.Composable
import com.ink1804.feature.root.api.RootComponent
import com.ink1804.feature.root.api.RootScreenProvider

class RootScreenProviderImpl: RootScreenProvider {
    @Composable
    override fun Content(component: RootComponent) {
        RootScreen(component)
    }
}
