package com.ink1804.feature.debug

import com.arkivanov.decompose.ComponentContext

interface DebugFeatureTogglesComponent
interface DebugUserInfoComponent

interface DebugMenuComponent {

    sealed interface Child {
        class FeatureToggle() : Child
        class UserInfo() : Child
    }

    fun interface Factory {
        operator fun invoke(context: ComponentContext): DebugMenuComponent
    }
}
