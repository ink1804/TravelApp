package com.ink1804.core.ui.theme.palette

import androidx.compose.ui.graphics.Color

interface ColorPalette {
    val primary: Color // button / primary accent
    val onPrimary: Color // text on button
    val background: Color // screen background
    val onBackground: Color // text on screen background
    val surface: Color // status bar / bottom bar / cards
    val onSurface: Color
    val surfaceVariant: Color // background for bottom bar
    val onSurfaceVariant: Color
    val tertiary: Color // secondary accent
    val onTertiary: Color
}
