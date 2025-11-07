package com.ink1804.feature.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.ink1804.feature.home.HomeComponent
import kotlinx.serialization.Serializable

internal class RootComponentImpl(
    componentContext: ComponentContext,
    private val homeComponentFactory: HomeComponent.Factory,
) : RootComponent, ComponentContext by componentContext {

    private val homeComponent by lazy { homeComponentFactory.invoke(componentContext) }
    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Home,
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild,
        key = "RootComponentStack"
    )

    fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        ChildConfig.Home -> RootComponent.Child.Home(homeComponent)
        ChildConfig.Onboarding -> RootComponent.Child.Onboarding()
    }

    class Factory(
        private val homeComponentFactory: HomeComponent.Factory,
    ) : RootComponent.Factory {
        override fun invoke(context: ComponentContext): RootComponent = RootComponentImpl(
            componentContext = context,
            homeComponentFactory = homeComponentFactory,
        )
    }
}

@Serializable
sealed interface ChildConfig {

    @Serializable
    data object Home : ChildConfig

    @Serializable
    data object Onboarding : ChildConfig
}
