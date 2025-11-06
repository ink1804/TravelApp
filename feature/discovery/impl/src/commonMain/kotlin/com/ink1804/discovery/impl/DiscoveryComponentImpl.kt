package com.ink1804.discovery.impl

import com.arkivanov.decompose.ComponentContext
import com.ink1804.discovery.api.DiscoveryComponent
import com.ink1804.discovery.api.DiscoveryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DiscoveryComponentImpl(
    componentContext: ComponentContext,
) : DiscoveryComponent {
    /*
    create coroutine scope
     */
    private val _state = MutableStateFlow(DiscoveryState("hello im root"))
    override val state: StateFlow<DiscoveryState> = _state.asStateFlow()


    class Factory() : DiscoveryComponent.Factory {
        override fun invoke(context: ComponentContext): DiscoveryComponent = DiscoveryComponentImpl(
            componentContext = context,
        )
    }
}
