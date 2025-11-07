package com.ink1804.feature.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ink1804.feature.home.HomeComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Home(val component: HomeComponent) : Child
        class Onboarding() : Child
    }

    fun interface Factory {
        operator fun invoke(context: ComponentContext): RootComponent
    }
}