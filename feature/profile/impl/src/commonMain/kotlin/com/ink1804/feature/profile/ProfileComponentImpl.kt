package com.ink1804.feature.profile

import com.arkivanov.decompose.ComponentContext
import com.ink1804.core.coroutines.createCoroutineScope
import com.ink1804.core.settings.AppThemeColorScheme
import com.ink1804.core.settings.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileComponentImpl(
    componentContext: ComponentContext,
    private val settingsRepository: SettingsRepository,
) : ProfileComponent {

    private val scope = componentContext.createCoroutineScope()

    private val _state = MutableStateFlow(ProfileState("hello im root"))
    override val state: StateFlow<ProfileState> = _state.asStateFlow()

    override fun changeTheme() {
        scope.launch {
            if (settingsRepository.isDarkTheme()) {
                settingsRepository.updateAppColorScheme(AppThemeColorScheme.DefaultLight)
            } else {
                settingsRepository.updateAppColorScheme(AppThemeColorScheme.DefaultDark)
            }
        }
    }

    class Factory(
        private val settingsRepository: SettingsRepository
    ) : ProfileComponent.Factory {
        override fun invoke(context: ComponentContext): ProfileComponent = ProfileComponentImpl(
            componentContext = context,
            settingsRepository = settingsRepository
        )
    }
}
