package com.ink1804.feature.root.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ink1804.discovery.api.DiscoveryComponent
import com.ink1804.profile.api.ProfileComponent


interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onTabSelected(tab: RootTab)

    sealed interface Child {
        class Discovery(val component: DiscoveryComponent) : Child
        class Profile(val component: ProfileComponent) : Child
    }

    fun interface Factory {
        operator fun invoke(context: ComponentContext): RootComponent
    }
}

sealed interface RootTab {
    data object Discovery: RootTab
    data object Profile: RootTab
}