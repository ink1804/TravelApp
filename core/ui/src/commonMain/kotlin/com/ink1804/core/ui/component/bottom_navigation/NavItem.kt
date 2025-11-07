package com.ink1804.core.ui.component.bottom_navigation

import androidx.compose.ui.graphics.vector.ImageVector

/* Customize this to use Res drawables for CMP */
data class NavItem(
    val icon: ImageVector,
    val selectedIcon: ImageVector? = null,
    val label: String,
    val badgeCount: Int? = null
)
