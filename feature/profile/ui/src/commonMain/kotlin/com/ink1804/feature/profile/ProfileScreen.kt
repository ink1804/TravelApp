package com.ink1804.feature.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ink1804.core.ui.component.DarkOverlay

@Composable
fun ProfileScreen(component: ProfileComponent) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        DarkOverlay(
            modifier = Modifier.align(Alignment.BottomCenter),
            fraction = 0.08f,
            colors = listOf(Color.Transparent, Color(0xD6000000)),
        )

        Text(text = "profile screen")

        Button(onClick = component::changeTheme) {
            Text(text = "change theme color")
        }
    }
}
