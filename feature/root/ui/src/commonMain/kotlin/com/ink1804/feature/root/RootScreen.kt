package com.ink1804.feature.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.ink1804.feature.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RootScreen(component: RootComponent) {
    val state by component.childStack.subscribeAsState()

    Content(state)
}

@Composable
private fun Content(
    state: ChildStack<*, RootComponent.Child>
){
    Box(
        modifier = Modifier.fillMaxSize(),
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
    }
}


@Composable
@Preview
private fun Preview() {
//    Content()
}
