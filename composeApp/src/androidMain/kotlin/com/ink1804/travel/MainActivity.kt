package com.ink1804.travel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import com.arkivanov.decompose.DefaultComponentContext
import com.ink1804.feature.root.api.RootEntry
import com.ink1804.feature.root.api.RootScreenProvider
import org.koin.android.ext.android.getKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val rootEntry: RootEntry = getKoin().get()
        val rootScreenProvider: RootScreenProvider = getKoin().get()
        val context = DefaultComponentContext(lifecycle)

        setContent {
            MaterialTheme {
                val root = remember { rootEntry.create(context) }
                rootScreenProvider.Content(root)
            }
        }
    }
}
