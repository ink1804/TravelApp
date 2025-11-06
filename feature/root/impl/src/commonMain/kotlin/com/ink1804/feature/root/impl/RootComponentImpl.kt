package com.ink1804.feature.root.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.ink1804.discovery.api.DiscoveryComponent
import com.ink1804.feature.root.api.RootComponent
import com.ink1804.feature.root.api.RootTab
import com.ink1804.profile.api.ProfileComponent
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
    private val profileComponentFactory: ProfileComponent.Factory,
    private val discoveryComponentFactory: DiscoveryComponent.Factory,
) : RootComponent, ComponentContext by componentContext {

    private val profileComponent by lazy { profileComponentFactory.invoke(componentContext) }
    private val discoveryComponent by lazy { discoveryComponentFactory.invoke(componentContext) }
    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Discovery,
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    )

    fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        ChildConfig.Discovery -> RootComponent.Child.Discovery(discoveryComponent)
        ChildConfig.Profile -> RootComponent.Child.Profile(profileComponent)
    }

    override fun onTabSelected(tab: RootTab) {
        val configuration = tab.toConfiguration()
        navigation.bringToFront(configuration)
    }

    private fun RootTab.toConfiguration(): ChildConfig = when (this) {
        RootTab.Discovery -> ChildConfig.Discovery
        RootTab.Profile -> ChildConfig.Profile
    }

    class Factory(
        private val profileComponentFactory: ProfileComponent.Factory,
        private val discoveryComponentFactory: DiscoveryComponent.Factory,
    ) : RootComponent.Factory {
        override fun invoke(context: ComponentContext): RootComponent = RootComponentImpl(
            componentContext = context,
            profileComponentFactory = profileComponentFactory,
            discoveryComponentFactory = discoveryComponentFactory,
        )
    }
}

@Serializable
sealed interface ChildConfig {

    @Serializable
    data object Discovery : ChildConfig

    @Serializable
    data object Profile : ChildConfig
}
