package com.ink1804.feature.discovery

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface DiscoveryComponent {
    val state: StateFlow<DiscoveryState>

    fun interface Factory {
        operator fun invoke(context: ComponentContext): DiscoveryComponent
    }
}

data class DiscoveryState(
    val text: String,
)