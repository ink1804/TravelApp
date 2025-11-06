package com.ink1804.profile.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {
    val state: StateFlow<ProfileState>

    fun interface Factory {
        operator fun invoke(context: ComponentContext): ProfileComponent
    }
}

data class ProfileState(
    val text: String,
)