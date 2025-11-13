package com.ink1804.feature.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.ink1804.feature.discovery.DiscoveryComponent
import com.ink1804.feature.profile.ProfileComponent
import kotlinx.serialization.Serializable

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val profileComponentFactory: ProfileComponent.Factory,
    private val discoveryComponentFactory: DiscoveryComponent.Factory,
    private val analytics: HomeAnalytics,
) : HomeComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ChildConfig>()

    private val tabs = listOf(
        HomeTab.Discovery,
        HomeTab.Profile,
    )

    override val childStack: Value<ChildStack<*, HomeComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Discovery.also { analytics.screenOpened(it.screenId) },
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild,
        key = "HomeComponentStack"
    )

    fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): HomeComponent.Child = when (config) {
        ChildConfig.Discovery -> HomeComponent.Child.Discovery(discoveryComponentFactory.invoke(componentContext))
        ChildConfig.Profile -> HomeComponent.Child.Profile(profileComponentFactory.invoke(componentContext))
    }

    override fun onTabSelected(index: Int) {
        val configuration = tabs[index].toConfiguration()
        analytics.screenOpened(configuration.screenId)
        navigation.bringToFront(configuration)
    }

    private fun HomeTab.toConfiguration(): ChildConfig = when (this) {
        HomeTab.Discovery -> ChildConfig.Discovery
        HomeTab.Profile -> ChildConfig.Profile
    }

    class Factory(
        private val profileComponentFactory: ProfileComponent.Factory,
        private val discoveryComponentFactory: DiscoveryComponent.Factory,
        private val analytics: HomeAnalytics,
    ) : HomeComponent.Factory {
        override fun invoke(context: ComponentContext): HomeComponent = HomeComponentImpl(
            componentContext = context,
            profileComponentFactory = profileComponentFactory,
            discoveryComponentFactory = discoveryComponentFactory,
            analytics = analytics,
        )
    }
}

@Serializable
sealed class ChildConfig(val screenId: String) {

    @Serializable
    data object Discovery : ChildConfig("discovery")

    @Serializable
    data object Profile : ChildConfig("profile")
}
