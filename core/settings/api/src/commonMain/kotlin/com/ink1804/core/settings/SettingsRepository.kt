package com.ink1804.core.settings

import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {
    val appColorScheme: StateFlow<AppThemeColorScheme>
    fun isDarkTheme(): Boolean
    suspend fun updateAppColorScheme(scheme: AppThemeColorScheme)
}
