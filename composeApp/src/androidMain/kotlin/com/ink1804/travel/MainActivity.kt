package com.ink1804.travel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowInsetsControllerCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.ink1804.feature.root.RootComponent
import com.ink1804.feature.root.RootScreen
import org.koin.android.ext.android.getKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val rootComponentFactory: RootComponent.Factory = getKoin().get()
        val context = DefaultComponentContext(lifecycle)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        setContent {
            val colors = if (true) darkColorScheme() else lightColorScheme(
                onSurface = Color.Black,
            )

            MaterialTheme(
                colorScheme = colors,
            ) {
                val root = remember { rootComponentFactory.invoke(context) }
                RootScreen(root)
            }
        }
    }
}
