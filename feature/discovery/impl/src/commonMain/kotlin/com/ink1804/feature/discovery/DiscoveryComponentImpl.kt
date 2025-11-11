package com.ink1804.feature.discovery

import com.arkivanov.decompose.ComponentContext
import com.ink1804.core.coroutines.createCoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class DiscoveryComponentImpl(
    componentContext: ComponentContext,
) : DiscoveryComponent {
    /*
    create coroutine scope
     */
    private val scope = componentContext.createCoroutineScope()
    private val _state = MutableStateFlow(DiscoveryState("hello im root"))
    override val state: StateFlow<DiscoveryState> = _state.asStateFlow()


    class Factory() : DiscoveryComponent.Factory {
        override fun invoke(context: ComponentContext): DiscoveryComponent = DiscoveryComponentImpl(
            componentContext = context,
        )
    }
}
