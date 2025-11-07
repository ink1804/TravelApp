package com.ink1804.travel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
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

        setContent {
            MaterialTheme {
                val root = remember { rootComponentFactory.invoke(context) }
                RootScreen(root)
            }
        }
    }
}
