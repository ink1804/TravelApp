package com.ink1804.home.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.ink1804.discovery.api.DiscoveryComponent
import com.ink1804.home.api.HomeComponent
import com.ink1804.home.api.HomeTab
import com.ink1804.profile.api.ProfileComponent
import kotlinx.serialization.Serializable

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val profileComponentFactory: ProfileComponent.Factory,
    private val discoveryComponentFactory: DiscoveryComponent.Factory,
) : HomeComponent, ComponentContext by componentContext {

    private val profileComponent by lazy { profileComponentFactory.invoke(componentContext) }
    private val discoveryComponent by lazy { discoveryComponentFactory.invoke(componentContext) }
    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: Value<ChildStack<*, HomeComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Discovery,
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild,
        key = "HomeComponentStack"
    )

    fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): HomeComponent.Child = when (config) {
        ChildConfig.Discovery -> HomeComponent.Child.Discovery(discoveryComponent)
        ChildConfig.Profile -> HomeComponent.Child.Profile(profileComponent)
    }

    override fun onTabSelected(tab: HomeTab) {
        val configuration = tab.toConfiguration()
        navigation.bringToFront(configuration)
    }

    private fun HomeTab.toConfiguration(): ChildConfig = when (this) {
        HomeTab.Discovery -> ChildConfig.Discovery
        HomeTab.Profile -> ChildConfig.Profile
    }

    class Factory(
        private val profileComponentFactory: ProfileComponent.Factory,
        private val discoveryComponentFactory: DiscoveryComponent.Factory,
    ) : HomeComponent.Factory {
        override fun invoke(context: ComponentContext): HomeComponent = HomeComponentImpl(
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
