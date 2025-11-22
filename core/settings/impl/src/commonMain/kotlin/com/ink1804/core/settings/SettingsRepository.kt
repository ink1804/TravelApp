package com.ink1804.core.settings

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsRepositoryImpl : SettingsRepository {

    private val _appColorScheme = MutableStateFlow(AppThemeColorScheme.DefaultLight)
    override val appColorScheme: StateFlow<AppThemeColorScheme> = _appColorScheme.asStateFlow()

    override fun isDarkTheme(): Boolean {
        return _appColorScheme.value.isDark
    }

    override suspend fun updateAppColorScheme(scheme: AppThemeColorScheme) {
        _appColorScheme.value = scheme
    }
}
