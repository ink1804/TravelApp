package com.ink1804.feature.debug

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import kotlinx.serialization.Serializable

class DebugFeatureTogglesComponentImpl : DebugFeatureTogglesComponent
class DebugUserInfoComponentImpl : DebugUserInfoComponent

class DebugMenuComponentImpl(
    componentContext: ComponentContext,
) : DebugMenuComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ChildConfig>()

    fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): DebugMenuComponent.Child = when (config) {
        ChildConfig.FeatureToggle -> DebugMenuComponent.Child.FeatureToggle()
        ChildConfig.UserInfo -> DebugMenuComponent.Child.UserInfo()
    }


    class Factory() : DebugMenuComponent.Factory {
        override fun invoke(context: ComponentContext): DebugMenuComponent = DebugMenuComponentImpl(
            componentContext = context,
        )
    }
}

@Serializable
sealed class ChildConfig(val screenId: String) {

    @Serializable
    data object FeatureToggle : ChildConfig("debug_feature_toggle")

    @Serializable
    data object UserInfo : ChildConfig("debug_user_info")
}
