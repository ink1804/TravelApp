package com.ink1804.feature.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ink1804.feature.discovery.DiscoveryComponent
import com.ink1804.feature.profile.ProfileComponent


interface HomeComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onTabSelected(index: Int)

    sealed interface Child {
        class Discovery(val component: DiscoveryComponent) : Child
        class Profile(val component: ProfileComponent) : Child
    }

    fun interface Factory {
        operator fun invoke(context: ComponentContext): HomeComponent
    }
}

sealed interface HomeTab {
    data object Discovery: HomeTab
    data object Profile: HomeTab
}