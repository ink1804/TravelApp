package com.ink1804.core.ui.theme

import androidx.compose.runtime.Composable
import com.ink1804.core.settings.AppThemeColorScheme

@Composable
expect fun AppTheme(
    selectedTheme: AppThemeColorScheme,
    content: @Composable () -> Unit
)
