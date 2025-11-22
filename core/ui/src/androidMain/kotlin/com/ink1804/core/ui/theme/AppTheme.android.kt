package com.ink1804.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import com.ink1804.core.settings.AppThemeColorScheme

@Composable
actual fun AppTheme(
    selectedTheme: AppThemeColorScheme,
    content: @Composable () -> Unit
) {
    val palette = getSelectedColorScheme(selectedTheme)

    MaterialTheme(
        colorScheme = palette,
        typography = Typography(),
        content = content
    )
}
