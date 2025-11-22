package com.ink1804.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import com.ink1804.core.settings.AppThemeColorScheme
import com.ink1804.core.ui.theme.palette.ColorPalette
import com.ink1804.core.ui.theme.palette.DefaultDarkColorPalette
import com.ink1804.core.ui.theme.palette.DefaultLightColorPalette

fun getSelectedColorScheme(appThemeSelected: AppThemeColorScheme): ColorScheme {
    return when (appThemeSelected) {
        AppThemeColorScheme.DefaultLight -> DefaultLightColorPalette
        AppThemeColorScheme.DefaultDark -> DefaultDarkColorPalette
    }.toColorScheme()
}

fun ColorPalette.toColorScheme(): ColorScheme {
    return lightColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        tertiary = tertiary,
        onTertiary = onTertiary,
    )
}
