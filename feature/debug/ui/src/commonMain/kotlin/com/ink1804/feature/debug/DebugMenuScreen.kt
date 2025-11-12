package com.ink1804.feature.debug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ink1804.core.logger.Log
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DebugMenuScreen(debugMenuComponent: DebugMenuComponent) {
    Content()
}

@Composable
private fun Content() {
    Column(
        Modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier,
            onClick = { Log.d("myLogs", "features click")}
        ) {
            Text("Features")
        }

        Button(
            modifier = Modifier,
            onClick = { Log.d("myLogs", "info click")}
        ) {
            Text("Info")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Content()
}