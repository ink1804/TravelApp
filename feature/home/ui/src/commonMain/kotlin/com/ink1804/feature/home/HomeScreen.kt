package com.ink1804.feature.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    val state by component.childStack.subscribeAsState()
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            CurvedBottomNavigation(
                items = navItems,
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                    component.onTabSelected(selectedIndex)
                }
            )
        }
    ) {
        Children(
            stack = state,
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
    NavItem(
        label = "Discovery",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
    ),
    NavItem(
        label = "Profile",
        icon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
    ),
)