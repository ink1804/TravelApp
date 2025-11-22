package com.ink1804.feature.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.ink1804.feature.debug.DebugMenuScreen
import com.ink1804.feature.home.HomeScreen
import com.ink1804.feature.onboarding.ui.OnboardingScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RootScreen(component: RootComponent) {
    val state by component.childStack.subscribeAsState()
    val overlay by component.overlayStack.subscribeAsState()

    Content(
        state = state,
        overlay = overlay,
        onCloseModalScreen = { component.closeDebug() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    state: ChildStack<*, RootComponent.Child>,
    overlay: ChildSlot<*, RootComponent.OverlayChild>,
    onCloseModalScreen: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        Children(
            stack = state,
            animation = null,
            modifier = Modifier,
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.Onboarding -> OnboardingScreen(child.component)
                is RootComponent.Child.Home -> HomeScreen(child.component)
            }
        }

        when (val child = overlay.child?.instance) {
            is RootComponent.OverlayChild.DebugMenu -> {
                ModalBottomSheet(onDismissRequest = onCloseModalScreen) {
                    DebugMenuScreen(child.component)
                }
            }

            null -> Unit
        }

        Spacer(
            modifier = Modifier
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        )

        Spacer(
            modifier = Modifier
                .windowInsetsBottomHeight(WindowInsets.navigationBars)
                .align(Alignment.BottomCenter)
                .zIndex(2f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        )
    }
}


@Composable
@Preview
private fun Preview() {
//    Content()
}
