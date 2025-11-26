package com.ink1804.core.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.ink1804.core.settings.AppThemeColorScheme
import com.ink1804.core.ui.theme.palette.ColorPalette

@Composable
fun AppTheme(
    selectedTheme: AppThemeColorScheme,
    content: @Composable () -> Unit
) {
    val palette = getSelectedColorScheme(selectedTheme)
    AnimatedAppTheme(palette, content)
}

@Composable
private fun AnimatedAppTheme(
    targetPalette: ColorScheme,
    content: @Composable () -> Unit
) {
    val primary by animateColorAsState(targetPalette.primary, label = "primary")
    val onPrimary by animateColorAsState(targetPalette.onPrimary, label = "onPrimary")
    val background by animateColorAsState(targetPalette.background, label = "background")
    val onBackground by animateColorAsState(targetPalette.onBackground, label = "onBackground")
    val surface by animateColorAsState(targetPalette.surface, label = "surface")
    val onSurface by animateColorAsState(targetPalette.onSurface, label = "onSurface")
    val surfaceVariant by animateColorAsState(targetPalette.surfaceVariant, label = "surfaceVariant")
    val onSurfaceVariant by animateColorAsState(targetPalette.onSurfaceVariant, label = "onSurfaceVariant")
    val tertiary by animateColorAsState(targetPalette.tertiary, label = "tertiary")
    val onTertiary by animateColorAsState(targetPalette.onTertiary, label = "onTertiary")

    val animatedPalette = object : ColorPalette {
        override val primary = primary
        override val onPrimary = onPrimary
        override val background = background
        override val onBackground = onBackground
        override val surface = surface
        override val onSurface = onSurface
        override val surfaceVariant = surfaceVariant
        override val onSurfaceVariant = onSurfaceVariant
        override val tertiary = tertiary
        override val onTertiary = onTertiary
    }

    val colorScheme = animatedPalette.toColorScheme()

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
