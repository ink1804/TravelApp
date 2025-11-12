package com.ink1804.feature.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.ink1804.feature.debug.DebugMenuScreen
import com.ink1804.feature.home.HomeScreen
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
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        Children(
            stack = state,
            animation = null,
            modifier = Modifier,
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.Onboarding -> Box {}
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
    }
}


@Composable
@Preview
private fun Preview() {
//    Content()
}
