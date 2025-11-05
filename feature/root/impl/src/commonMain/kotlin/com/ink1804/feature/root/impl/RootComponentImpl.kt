package com.ink1804.feature.root.impl

import com.arkivanov.decompose.ComponentContext
import com.ink1804.feature.root.api.RootComponent
import com.ink1804.feature.root.api.RootState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RootComponentImpl(
    componentContext: ComponentContext,
) : RootComponent {
    /*
    create coroutine scope
     */
    private val _state = MutableStateFlow(RootState("hello im root"))
    override val state: StateFlow<RootState> = _state.asStateFlow()
}
