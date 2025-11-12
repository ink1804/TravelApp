package com.ink1804.feature.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnPause
import com.arkivanov.essenty.lifecycle.doOnResume
import com.ink1804.core.coroutines.createCoroutineScope
import com.ink1804.core.platform.ShakeDetector
import com.ink1804.feature.debug.DebugMenuComponent
import com.ink1804.feature.home.HomeComponent
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

internal class RootComponentImpl(
    componentContext: ComponentContext,
    private val homeComponentFactory: HomeComponent.Factory,
    private val debugMenuComponentFactory: DebugMenuComponent.Factory,
    private val shakeDetector: ShakeDetector,
) : RootComponent, ComponentContext by componentContext {

    private val scope = componentContext.createCoroutineScope()

    init {
        componentContext.lifecycle.apply {
            doOnResume { shakeDetector.start() }
            doOnPause { shakeDetector.stop() }
        }

        scope.launch {
            shakeDetector.shakes.collect {
                openDebugMenu()
            }
        }
    }

    private val homeComponent by lazy { homeComponentFactory.invoke(componentContext) }
    private val debugMenuComponent by lazy { debugMenuComponentFactory.invoke(componentContext) }

    private val navigation = StackNavigation<ChildConfig>()
    private val debugNav = SlotNavigation<OverlayChildConfig>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Home,
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild,
        key = "RootComponentStack"
    )

    override val overlayStack: Value<ChildSlot<*, RootComponent.OverlayChild>> = childSlot(
        source = debugNav,
        serializer = OverlayChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createOverlayChild,
        key = "RootComponentOverlayStack"
    )

    override fun closeDebug() {
        debugNav.dismiss()
    }

    private fun openDebugMenu() {
        debugNav.activate(OverlayChildConfig.DebugMenu)
    }

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        ChildConfig.Home -> RootComponent.Child.Home(homeComponent)
        ChildConfig.Onboarding -> RootComponent.Child.Onboarding()
    }

    private fun createOverlayChild(
        config: OverlayChildConfig,
        componentContext: ComponentContext
    ): RootComponent.OverlayChild = when (config) {
        OverlayChildConfig.DebugMenu -> RootComponent.OverlayChild.DebugMenu(debugMenuComponent)
    }

    class Factory(
        private val homeComponentFactory: HomeComponent.Factory,
        private val debugMenuComponentFactory: DebugMenuComponent.Factory,
        private val shakeDetector: ShakeDetector,
    ) : RootComponent.Factory {
        override fun invoke(context: ComponentContext): RootComponent = RootComponentImpl(
            componentContext = context,
            homeComponentFactory = homeComponentFactory,
            debugMenuComponentFactory = debugMenuComponentFactory,
            shakeDetector = shakeDetector,
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

@Serializable
sealed interface OverlayChildConfig {
    @Serializable
    data object DebugMenu : OverlayChildConfig
}
