package com.ink1804.feature.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.ink1804.core.ui.component.bottom_navigation.CurvedBottomNavigation
import com.ink1804.core.ui.component.bottom_navigation.NavItem
import com.ink1804.feature.discovery.DiscoveryScreen
import com.ink1804.feature.profile.ProfileScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(component: HomeComponent) {
    val childStack by component.childStack.subscribeAsState()

    val currentTab = when (childStack.active.instance) {
        is HomeComponent.Child.Discovery -> HomeTab.Discovery
        is HomeComponent.Child.Profile -> HomeTab.Profile
    }

    Scaffold(
        bottomBar = {
            CurvedBottomNavigation(
                items = navItems,
                selectedTab = currentTab,
                onItemSelected = component::onTabSelected
            )
        }
    ) {
        Children(
            stack = childStack,
            animation = null,
            modifier = Modifier,
        ) {
            when (val child = it.instance) {
                is HomeComponent.Child.Discovery -> DiscoveryScreen(child.component)
                is HomeComponent.Child.Profile -> ProfileScreen(child.component)
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
//    RootScreen()
}

val navItems = listOf(
    NavItem<HomeTab>(
        label = "Discovery",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        tab = HomeTab.Discovery
    ),
    NavItem<HomeTab>(
        label = "Profile",
        icon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        tab = HomeTab.Profile
    ),
)