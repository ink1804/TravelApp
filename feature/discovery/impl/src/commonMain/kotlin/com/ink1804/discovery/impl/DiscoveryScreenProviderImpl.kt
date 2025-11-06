package com.ink1804.discovery.impl

import androidx.compose.runtime.Composable
import com.ink1804.discovery.api.DiscoveryComponent
import com.ink1804.discovery.api.DiscoveryScreenProvider

class DiscoveryScreenProviderImpl : DiscoveryScreenProvider {
    @Composable
    override fun Content(component: DiscoveryComponent) {
        DiscoveryScreen(component)
    }
}
