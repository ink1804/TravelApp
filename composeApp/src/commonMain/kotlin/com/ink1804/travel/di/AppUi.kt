package com.ink1804.travel.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ink1804.core.settings.SettingsRepository
import com.ink1804.core.ui.theme.AppTheme

@Composable
fun AppUi(
    settingsRepository: SettingsRepository,
    content: @Composable () -> Unit,
) {
    val appColorScheme by settingsRepository.appColorScheme.collectAsState()
    AppTheme(
        selectedTheme = appColorScheme,
    ) {
        content()
    }
}
