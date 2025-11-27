package com.ink1804.travel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.core.view.WindowInsetsControllerCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.ink1804.core.settings.SettingsRepository
import com.ink1804.feature.root.RootComponent
import com.ink1804.feature.root.RootScreen
import org.koin.android.ext.android.getKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val rootComponentFactory: RootComponent.Factory = getKoin().get()
        val settingsRepository: SettingsRepository = getKoin().get()
        val context = DefaultComponentContext(lifecycle)

        setContent {
            AppUi(
                settingsRepository = settingsRepository,
            ) {
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = !settingsRepository.isDarkTheme()
                val root = remember { rootComponentFactory.invoke(context) }
                RootScreen(root)
            }
        }
    }
}
