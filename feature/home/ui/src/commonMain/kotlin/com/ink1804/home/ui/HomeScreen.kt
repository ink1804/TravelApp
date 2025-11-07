package com.ink1804.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.ink1804.discovery.ui.DiscoveryScreen
import com.ink1804.home.api.HomeComponent
import com.ink1804.home.api.HomeTab
import com.ink1804.profile.ui.ProfileScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(component: HomeComponent) {
    val state by component.childStack.subscribeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
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
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                modifier = Modifier,
                onClick = { component.onTabSelected(HomeTab.Discovery) }
            ) {
                Text("Discovery")
            }
            Button(
                modifier = Modifier.padding(start = 10.dp),
                onClick = { component.onTabSelected(HomeTab.Profile) }
            ) {
                Text("Profile")
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
//    RootScreen()
}
