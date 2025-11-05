package com.ink1804.feature.root.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow


interface RootComponent {
    val state: StateFlow<RootState>
}

fun interface RootEntry {
    fun create(ctx: ComponentContext /*args*/): RootComponent
}

interface RootScreenProvider {
    @Composable
    fun Content(component: RootComponent)
}

data class RootState(
    val text: String,
)