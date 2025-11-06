package com.ink1804.profile.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {
    val state: StateFlow<ProfileState>
}

fun interface ProfileEntry {
    fun create(ctx: ComponentContext /*args*/): ProfileComponent
}

interface ProfileScreenProvider {
    @Composable
    fun Content(component: ProfileComponent)
}

data class ProfileState(
    val text: String,
)