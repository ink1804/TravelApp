package com.ink1804.feature.discovery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ink1804.core.resources.AppDrawable
import com.ink1804.core.resources.AppStrings
import com.ink1804.core.ui.component.DarkOverlay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import travelapp.core.resources.generated.resources.ic_heart
import travelapp.core.resources.generated.resources.title_discovery

@Composable
fun DiscoveryScreen(component: DiscoveryComponent) {
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

        Text(text = stringResource(AppStrings.title_discovery))

        Image(
            modifier = Modifier
                .systemBarsPadding()
                .align(Alignment.TopCenter)
                .size(44.dp),
            painter = painterResource(AppDrawable.ic_heart),
            contentDescription = null,
        )
    }
}
