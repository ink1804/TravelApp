package com.ink1804.feature.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.ink1804.core.coroutines.createCoroutineScope
import com.ink1804.core.logger.Log
import com.ink1804.feature.discovery.DiscoveryComponent
import com.ink1804.feature.profile.ProfileComponent
import com.ink1804.testapi.TestApi
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val profileComponentFactory: ProfileComponent.Factory,
    private val discoveryComponentFactory: DiscoveryComponent.Factory,
    private val analytics: HomeAnalytics,
    private val testApi: TestApi,
) : HomeComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ChildConfig>()
    private val coroutineScope = componentContext.createCoroutineScope()

    override val childStack: Value<ChildStack<*, HomeComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Discovery.also { analytics.screenOpened(it.screenId) },
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild,
        key = "HomeComponentStack"
    )

    init {
        coroutineScope.launch {
            testApi.put().also { Log.i("myLogs", "User added to db") }
            testApi.get()?.also { Log.i("myLogs", "User: ${it.name}") }
        }
    }

    fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): HomeComponent.Child = when (config) {
        ChildConfig.Discovery -> HomeComponent.Child.Discovery(discoveryComponentFactory.invoke(componentContext))
        ChildConfig.Profile -> HomeComponent.Child.Profile(profileComponentFactory.invoke(componentContext))
    }

    override fun onTabSelected(tab: HomeTab) {
        val configuration = tab.toConfiguration()
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
        private val testApi: TestApi,
    ) : HomeComponent.Factory {
        override fun invoke(context: ComponentContext): HomeComponent = HomeComponentImpl(
            componentContext = context,
            profileComponentFactory = profileComponentFactory,
            discoveryComponentFactory = discoveryComponentFactory,
            analytics = analytics,
            testApi = testApi,
        )
    }
}

@Serializable
sealed class ChildConfig(
    val screenId: String
) {
    @Serializable
    data object Discovery : ChildConfig("discovery")

    @Serializable
    data object Profile : ChildConfig("profile")
}
