package com.ink1804.travel

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.ink1804.feature.root.api.RootEntry
import com.ink1804.feature.root.api.RootScreenProvider
import org.koin.compose.getKoin

fun MainViewController() = ComposeUIViewController {
    val rootEntry: RootEntry = getKoin().get()
    val rootUi: RootScreenProvider = getKoin().get()

    val lifecycle = remember { LifecycleRegistry() }
    DisposableEffect(Unit) {
        lifecycle.resume()
        onDispose { lifecycle.destroy() }
    }
    val componentContext = remember { DefaultComponentContext(lifecycle) }

    val root = remember { rootEntry.create(componentContext) }
    rootUi.Content(root)
}