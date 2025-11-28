package com.ink1804.travel

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.ink1804.core.settings.SettingsRepository
import com.ink1804.feature.root.RootComponent
import com.ink1804.feature.root.RootScreen
import org.koin.compose.getKoin
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val rootComponentFactory: RootComponent.Factory = getKoin().get()
    val settingsRepository: SettingsRepository = getKoin().get()

    val lifecycle = remember { LifecycleRegistry() }
    DisposableEffect(Unit) {
        lifecycle.resume()
        onDispose { lifecycle.destroy() }
    }
    val componentContext = remember { DefaultComponentContext(lifecycle) }
    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark

    AppUi(
        settingsRepository = settingsRepository,
    ) {
        val root = remember { rootComponentFactory.invoke(componentContext) }
        RootScreen(root)
    }
}
