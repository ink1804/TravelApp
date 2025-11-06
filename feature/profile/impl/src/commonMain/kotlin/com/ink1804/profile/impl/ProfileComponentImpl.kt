package com.ink1804.profile.impl

import com.arkivanov.decompose.ComponentContext
import com.ink1804.profile.api.ProfileComponent
import com.ink1804.profile.api.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileComponentImpl(
    componentContext: ComponentContext,
) : ProfileComponent {
    /*
    create coroutine scope
     */
    private val _state = MutableStateFlow(ProfileState("hello im root"))
    override val state: StateFlow<ProfileState> = _state.asStateFlow()
}
