package com.ink1804.feature.profile

import com.arkivanov.decompose.ComponentContext
import com.ink1804.core.coroutines.createCoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileComponentImpl(
    componentContext: ComponentContext,
) : ProfileComponent {

    private val scope = componentContext.createCoroutineScope()

    private val _state = MutableStateFlow(ProfileState("hello im root"))
    override val state: StateFlow<ProfileState> = _state.asStateFlow()


    class Factory() : ProfileComponent.Factory {
        override fun invoke(context: ComponentContext): ProfileComponent = ProfileComponentImpl(
            componentContext = context,
        )
    }
}
