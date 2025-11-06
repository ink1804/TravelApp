package com.ink1804.discovery.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface DiscoveryComponent {
    val state: StateFlow<DiscoveryState>
}

fun interface DiscoveryEntry {
    fun create(ctx: ComponentContext /*args*/): DiscoveryComponent
}

interface DiscoveryScreenProvider {
    @Composable
    fun Content(component: DiscoveryComponent)
}

data class DiscoveryState(
    val text: String,
)